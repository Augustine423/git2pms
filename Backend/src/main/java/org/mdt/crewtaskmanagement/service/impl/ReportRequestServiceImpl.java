package org.mdt.crewtaskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.approval.ApprovalDtoInput;
import org.mdt.crewtaskmanagement.dto.approval.ApprovalDtoOutput;
import org.mdt.crewtaskmanagement.dto.material.MaterialDto;
import org.mdt.crewtaskmanagement.dto.reportrequest.MaterialRequestDto;
import org.mdt.crewtaskmanagement.dto.reportrequest.ReportRequestDto;
import org.mdt.crewtaskmanagement.exception.DuplicateApprovalException;
import org.mdt.crewtaskmanagement.exception.ServiceBaseException;
import org.mdt.crewtaskmanagement.mapper.MaterialMapper;
import org.mdt.crewtaskmanagement.mapper.ReportRequestMapper;
import org.mdt.crewtaskmanagement.model.*;
import org.mdt.crewtaskmanagement.model.type.CrewRank;
import org.mdt.crewtaskmanagement.output.PageResult;
import org.mdt.crewtaskmanagement.repository.entity.*;
import org.mdt.crewtaskmanagement.service.ICrewService;
import org.mdt.crewtaskmanagement.service.IReportRequestService;
import org.mdt.crewtaskmanagement.utils.GetEntitesWithPageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
@RequiredArgsConstructor
public class ReportRequestServiceImpl implements IReportRequestService {


    private final MaterialRepository materialRepository;
    private final ReportRequestRepository reportRequestRepository;
    private final MaterialReportRequestRepository mrrRepository;
    private final TaskAssignmentRepository ts_dao;
    private final ApprovalRepository approvalRepository;
    private final MaterialServiceImpl materialService;
    private final ReportRequestMapper reportRequestMapper;
    private final ICrewService crewService;


    //get materials which are requested in that request;
    public List<MaterialDto> getMaterialsFromReportRequest(long reportRequestId) {
        List<Material> materials = mrrRepository.getMaterialsUsedInReportRequestByReportRequestId(reportRequestId);
        return materials.stream().map(MaterialMapper::toDto).collect(Collectors.toList());
    }



    //for previous version .. no longer need anymore
    public String assignMaterialsToReportRequest(long reportRequestId, long materialId, int quantity) {
        ReportRequest reportRequest = reportRequestRepository.findById(reportRequestId).orElseThrow();
        Material material = materialRepository.findById(materialId).orElseThrow();
        MaterialReportRequest mrr = new MaterialReportRequest();
        mrr.setMaterial(material);
        mrr.setReportRequest(reportRequest);
        mrrRepository.save(mrr);
        return "Added material " + material.getName() + " to report request " + reportRequest.getTitle();
    }


    //reports(pending) to approve from your crew
    public PageResult<ReportRequestDto> getReportRequestsToApprove(long crewId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ReportRequest> reportRequests = reportRequestRepository.findPendingReportsByLeader(crewId, pageable);
        return GetEntitesWithPageable.getAllWithPageable(pageable,reportRequests,reportRequestMapper::toDto);
    }

    //reports(pending) you request
    public PageResult<ReportRequestDto> getReportRequestsWaitingApprove(long crewId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ReportRequest> reportRequests = reportRequestRepository.findPendingReportsByCrew(crewId, pageable);
        return GetEntitesWithPageable.getAllWithPageable(pageable,reportRequests,reportRequestMapper::toDto);
    }


    // ✅ APPROVAL LOGIC
    public void addApprovalForReportRequest(ApprovalDtoInput input) throws DuplicateApprovalException, ServiceBaseException {
        ReportRequest reportRequest = reportRequestRepository.findById(input.getReportRequestId()).orElseThrow();
        Crew crew = crewService.getById(input.getCrewId() != 0L ? input.getCrewId() : crewService.getIdFromContext());
        boolean alreadyApproved = reportRequest.getApprovals().stream()
                .anyMatch(a -> a.getApprover().getId().equals(crew.getId()));
        if (alreadyApproved) {
            throw new DuplicateApprovalException(List.of("Already approved", "Duplicate not allowed"));
        }
        Approval approval = new Approval();
        approval.setApprovalTimestamp(LocalDateTime.now());
        approval.setApprover(crew);
        approval.setRank(crew.getCrewRank());
        approval.setReportRequest(reportRequest);
        approvalRepository.save(approval);
        reportRequest.addApproval(approval);
        reportRequest.setStatus(ReportRequest.ReportStatus.APPROVED);
        reportRequest.getMaterialReportRequests().forEach(mrr -> mrr.getMaterial().setStatus(Material.MaterialStatus.IN_USE));
        // ✅ If final approver
        //no need for now(because approver is crew leader only for now(may be change later)
        if (crew.getCrewRank() == CrewRank.CHIEF_ENGINEER) {
            reportRequest.setStatus(ReportRequest.ReportStatus.APPROVED);
            reportRequest.getMaterialReportRequests().forEach(mrr -> mrr.getMaterial().setStatus(Material.MaterialStatus.IN_USE));
        }
        reportRequestRepository.saveAndFlush(reportRequest);
    }

    // ✅ CREATE REPORT WITH MATERIALS
    public ReportRequestDto createReportWithMaterials(ReportRequestDto dto) throws ServiceBaseException {
        ReportRequest report = reportRequestMapper.fromDto(dto);
        if(!report.getApprovals().isEmpty()) {
            report.getTaskAssignment().setStatus(TaskAssignment.AssignmentStatus.ACTIVE);
        }
        ReportRequest saved = reportRequestRepository.save(report);
        if (dto.getRequestedMaterials() != null && !dto.getRequestedMaterials().isEmpty()) {
            List<MaterialReportRequest> mrrList = new ArrayList<>();
            for (MaterialRequestDto req : dto.getRequestedMaterials()) {
                List<Material> materials = materialRepository.findByMaterialName(req.getMaterialName(), PageRequest.of(0, req.getQuantity()));
                if(materials.isEmpty()) {
                    throw new ServiceBaseException("Material " + req.getMaterialName() + " not found");
                }
                for (Material material : materials) {
                    MaterialReportRequest mrr = new MaterialReportRequest();
                    mrr.setMaterial(material);
                    mrr.setReportRequest(saved);
                    mrrList.add(mrr);
                }
            }
            mrrRepository.saveAll(mrrList);
        }

        return reportRequestMapper.toDto(saved);
    }

    // ✅ GET APPROVAL DETAILS
    public List<ApprovalDtoOutput> getApprovalsForReportRequest(long reportRequestId) {
        var approvals = approvalRepository.getApprovalsByReportRequestId(reportRequestId);
        return approvals.stream()
                .map(ap -> ApprovalDtoOutput.builder()
                        .id(ap.getId())
                        .approver(ap.getApprover().getFirstName())
                        .approvedBy(ap.getApprover().getLastName())
                        .approvedDate(ap.getApprovalTimestamp().toString()) // format if needed
                        .reportRequestId(ap.getReportRequest().getId())
                        .rank(ap.getApprover().getCrewRank().name())
                        .build()
                ).collect(Collectors.toList());
    }

    // ✅ PAGINATED APPROVED REPORTS AS CREW
     public PageResult<ReportRequestDto> getApprovedReportRequestAsCrew(long crewId, int page, int size) {
       Pageable pageable = PageRequest.of(page, size);
       var reports = reportRequestRepository.findApprovedReportsByCrew(crewId, pageable);
       return GetEntitesWithPageable.getAllWithPageable(pageable, reports, reportRequestMapper::toDto);
   }


    // ✅ PAGINATED APPROVED REPORTS AS LEADER
    public PageResult<ReportRequestDto> getApprovedReportRequestForLeader(long crewId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var reports = reportRequestRepository.findApprovedReportsByLeader(crewId, pageable);
        return GetEntitesWithPageable.getAllWithPageable(pageable, reports, reportRequestMapper::toDto);
    }

    // ✅ ALL REPORTS PAGINATED
    public PageResult<ReportRequestDto> getAllReportRequests(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var reportRequests = reportRequestRepository.findAll(pageable);
        return GetEntitesWithPageable.getAllWithPageable(pageable, reportRequests, reportRequestMapper::toDto);
    }
    //GET BY ID
    public ReportRequestDto getReportRequestById(long reportRequestId) {
        return  reportRequestMapper.toDto( reportRequestRepository.findById(reportRequestId).orElseThrow());
    }

    public void cancelReportRequestById(long reportRequestId) {
        var reportRequest = reportRequestRepository.findById(reportRequestId).orElseThrow();
        if(reportRequest.getApprovals() == null){
            reportRequestRepository.deleteById(reportRequestId);
        }

    }
}
