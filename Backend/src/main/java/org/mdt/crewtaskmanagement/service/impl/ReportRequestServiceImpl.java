package org.mdt.crewtaskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.approval.ApprovalDtoInput;
import org.mdt.crewtaskmanagement.dto.approval.ApprovalDtoOutput;
import org.mdt.crewtaskmanagement.dto.material.MaterialDto;
import org.mdt.crewtaskmanagement.dto.reportrequest.MaterialRequestDto;
import org.mdt.crewtaskmanagement.dto.reportrequest.ReportRequestDto;
import org.mdt.crewtaskmanagement.exception.DuplicateApprovalException;
import org.mdt.crewtaskmanagement.mapper.MaterialMapper;
import org.mdt.crewtaskmanagement.mapper.ReportRequestMapper;
import org.mdt.crewtaskmanagement.model.*;
import org.mdt.crewtaskmanagement.model.type.CrewRank;
import org.mdt.crewtaskmanagement.output.PageResult;
import org.mdt.crewtaskmanagement.repository.entity.*;
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

    private final CrewRepository crewRepository;
    private final MaterialRepository materialRepository;
    private final ReportRequestRepository reportRequestRepository;
    private final MaterialReportRequestRepository mrrRepository;
    private final TaskAssignmentRepository ts_dao;
    private final ApprovalRepository approvalRepository;
    private final MaterialServiceImpl materialService;
    private final ReportRequestMapper reportRequestMapper;

    // ✅ GET MATERIALS for a REPORT
    public List<MaterialDto> getMaterialsFromReportRequest(long reportRequestId) {
        List<Material> materials = mrrRepository.getMaterialsUsedInReportRequestByReportRequestId(reportRequestId);
        return materials.stream().map(MaterialMapper::toDto).collect(Collectors.toList());
    }

    // ✅ ASSIGN MATERIALS to a REPORT
    public String assignMaterialsToReportRequest(long reportRequestId, long materialId, int quantity) {
        ReportRequest reportRequest = reportRequestRepository.findById(reportRequestId).orElseThrow();
        Material material = materialRepository.findById(materialId).orElseThrow();

        MaterialReportRequest mrr = new MaterialReportRequest();
        mrr.setMaterial(material);
        mrr.setReportRequest(reportRequest);
        mrrRepository.save(mrr);

        return "Added material " + material.getName() + " to report request " + reportRequest.getTitle();
    }

    // ✅ PAGINATED REPORT REQUESTS by RANK
//    public PageResult<ReportRequestDto> getPendingReportRequests(long crewId, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        CrewRank position = crewRepository.findById(crewId).orElseThrow().getCrewRank();
//
//        Page<ReportRequest> reportRequests = switch (position) {
//            case CHIEF_ENGINEER -> reportRequestRepository.getReportRequestsForFirstLeader(pageable);
//            case SECOND_ENGINEER -> reportRequestRepository.getReportRequestsForSecondLeader(pageable);
//            case THIRD_ENGINEER -> reportRequestRepository.getReportRequestsForThirdLeader(pageable);
//            default -> Page.empty();
//        };
//
//        return GetEntitesWithPageable.getAllWithPageable(pageable, reportRequests, reportRequestMapper::toDto);
//    }

    // ✅ APPROVAL LOGIC
    public void addApprovalForReportRequest(ApprovalDtoInput input) throws DuplicateApprovalException {
        ReportRequest reportRequest = reportRequestRepository.findById(input.getReportRequestId()).orElseThrow();
        Crew crew = crewRepository.findById(input.getCrewId()).orElseThrow();

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

        // ✅ If final approver
        if (crew.getCrewRank() == CrewRank.CHIEF_ENGINEER) {
            reportRequest.setStatus(ReportRequest.ReportStatus.APPROVED);
            reportRequest.getMaterialReportRequests().forEach(mrr -> mrr.getMaterial().setStatus(Material.MaterialStatus.IN_USE));
        }

        reportRequestRepository.save(reportRequest);
    }

    // ✅ CREATE REPORT WITH MATERIALS
    public ReportRequestDto createReportWithMaterials(ReportRequestDto dto) {
        ReportRequest report = reportRequestMapper.fromDto(dto);
        ReportRequest saved = reportRequestRepository.save(report);

        if (dto.getRequestedMaterials() != null && !dto.getRequestedMaterials().isEmpty()) {
            List<MaterialReportRequest> mrrList = new ArrayList<>();
            for (MaterialRequestDto req : dto.getRequestedMaterials()) {
                List<Material> materials = materialRepository.findByMaterialName(req.getMaterialName(), PageRequest.of(0, req.getQuantity()));


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

//    // ✅ PAGINATED APPROVED REPORTS
//    public PageResult<ReportRequestDto> getApprovedReportRequest(long crewId, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        var reports = reportRequestRepository.getApprovedReportRequests(crewId, pageable);
//        return GetEntitesWithPageable.getAllWithPageable(pageable, reports, reportRequestMapper::toDto);
//    }

    // ✅ ALL REPORTS PAGINATED
    public PageResult<ReportRequestDto> getAllReportRequests(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var reportRequests = reportRequestRepository.findAll(pageable);
        return GetEntitesWithPageable.getAllWithPageable(pageable, reportRequests, reportRequestMapper::toDto);
    }
}
