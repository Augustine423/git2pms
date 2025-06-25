package org.mdt.crewtaskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.approval.ApprovalDtoInput;
import org.mdt.crewtaskmanagement.dto.approval.ApprovalDtoOutput;
import org.mdt.crewtaskmanagement.dto.material.MaterialDto;
import org.mdt.crewtaskmanagement.dto.reportrequest.ReportRequestDto;
import org.mdt.crewtaskmanagement.mapper.MaterialMapper;
import org.mdt.crewtaskmanagement.mapper.ReportRequestMapper;
import org.mdt.crewtaskmanagement.model.*;
import org.mdt.crewtaskmanagement.model.type.CrewRank;
import org.mdt.crewtaskmanagement.repository.entity.*;
import org.mdt.crewtaskmanagement.service.ReportRequestService;
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
public class ReportRequestServiceImpl implements ReportRequestService {
    private final CrewRepository crewRepository;
    private final MaterialRepository materialRepository;
    private final ReportRequestRepository reportRequestRepository;
    private final MaterialReportRequestRepository mrrRepository;
    private final TaskAssignmentRepository ts_dao;
    private final ApprovalRepository approvalRepository;
    private final MaterialServiceImpl materialService;


//    @Override
//    public ReportRequestDto createReportRequest(ReportRequestDto reportRequestDto) {
//        ReportRequest reportRequest = new ReportRequest();
//        reportRequest.setTitle(reportRequestDto.getTitle());
//        reportRequest.setContent(reportRequestDto.getContent());
//        Crew crew = crewRepository.findById(reportRequestDto.getCrewId()).orElseThrow();
//        reportRequest.setCrew(crew);
//        TaskAssignment t_a = ts_dao.findById(reportRequestDto.getT_a_id()).orElseThrow();
//        reportRequest.setTaskAssignment(t_a);
//        reportRequest.setReportType(reportRequestDto.getReportType());
//        reportRequest.setRequestDate(LocalDate.now());
//        List<Material> materials = new ArrayList<>();
//        for( var m : reportRequestDto.getMaterialsIds()){
//             var usedMaterial = materialRepository.findById(m).get();
//             reportRequest.
//
//        }
//        re
//
//
//        reportRequestRepository.save(reportRequest);
//        return ReportRequestMapper.toDto(reportRequest);
//    }

  //  @Override
    public List<MaterialDto> getMaterialsFromReportRequest(long reportRequestId) {
        List<Material> materials = mrrRepository.getMaterialsUsedInReportRequestByReportRequestId(reportRequestId);
        List<MaterialDto> materialDtos = materials.stream()
                .map(m -> MaterialMapper.toDto(m))
                .collect(Collectors.toList());
        return materialDtos;
    }
    @Transactional
    public String assignMaterialsToReportRequest(long reportRequestId, long materialId, int quantity) {
        MaterialReportRequest mrr = new MaterialReportRequest();
        ReportRequest reportRequest = reportRequestRepository.findById(reportRequestId).orElseThrow();
        Material material = materialRepository.findById(materialId).orElseThrow();
        mrr.setMaterial(material);
        mrr.setReportRequest(reportRequest);
        mrrRepository.save(mrr);
        System.out.println(mrr.getReportRequest().getId() + " " + mrr.getMaterial().getId() + " " + mrr.getMaterial().getName());
        return "added material " + mrr.getMaterial().getName() + " to report request " + mrr.getReportRequest().getTitle();
    }


    public List<ReportRequestDto> getPendingReportRequests(long crewId) {
        CrewRank position = crewRepository.findById(crewId).get().getCrewRank();
        List<ReportRequest> reportRequests = new ArrayList<>();
        if(position == CrewRank.valueOf("first")){
            reportRequests = reportRequestRepository.getReportRequestsForFirstLeader();
        }
        else if(position == CrewRank.valueOf("second")){
            reportRequests = reportRequestRepository.getReportRequestsForSecondLeader();
        }
        else if(position == CrewRank.valueOf("third")){
            reportRequests = reportRequestRepository.getReportRequestsForThirdLeader();
        }
        return reportRequests.stream()
                .map(r -> ReportRequestMapper.toDto(r))
                .collect(Collectors.toList());

    }

//   @Override
    public void addApprovalForReportRequest( ApprovalDtoInput approvalDtoInput) {
        ReportRequest reportRequest = reportRequestRepository.findById(approvalDtoInput.getReportRequestId()).orElseThrow();

        Approval approval = new Approval();
        approval.setApprovalTimestamp(LocalDateTime.now());
        var crew =crewRepository.findById(approvalDtoInput.getCrewId()).orElseThrow();
        approval.setCrew(crew);

        approval.setPosition(CrewRank.valueOf(crew.getCrewRank().toString()));
        approval.setReportRequest(reportRequest);
        approvalRepository.save(approval);
        reportRequest.addApproval(approval);
        reportRequestRepository.save(reportRequest);

        if(crew.getCrewRank() == CrewRank.valueOf("first")){

            reportRequest.setApproved(true);

            reportRequest.getMaterialReportRequests().stream()
                .peek(m -> m.getMaterial().setUseStatus(true))
                .collect(Collectors.toList());

        }}



  //  @Override
    public List<ReportRequestDto> getApprovedReportRequest(long crewId) {
        return reportRequestRepository.getApprovedReportRequests(crewId).stream()
                .map(r -> ReportRequestMapper.toDto(r))
                .collect(Collectors.toList());
    }

    @Transactional
    public ReportRequestDto createReportWithMaterials(@RequestBody ReportRequestDto dto) {
        ReportRequest report = new ReportRequest();
        report.setTitle(dto.getTitle());
        report.setContent(dto.getContent());
        report.setReportType(dto.getReportType());
        System.out.println(dto.getRequestedMaterials()  + "materilas");

        TaskAssignment taskAssignment = ts_dao.findById(dto.getT_a_id()).orElseThrow();
        report.setTaskAssignment(taskAssignment);

        report.setRequestDate(LocalDate.parse(dto.getRequestDate().toString()));
        report.setCrew(crewRepository.findById(dto.getCrewId()).orElseThrow());
        var currentReport = reportRequestRepository.save(report);
        if(!dto.getRequestedMaterials().isEmpty()) {


            for (var m : dto.getRequestedMaterials()) {
                List<Material> materials = materialRepository.findByMaterialName(m.getMaterialName(), m.getQuantity()).orElseThrow();

                if (!materials.isEmpty()) {


                    for (Material material : materials) {
                        System.out.println(material.getId() + " " + material.getName());
                        MaterialReportRequest mrr = new MaterialReportRequest();
                        mrr.setMaterial(material);
                        System.out.println(material.getName());
                        mrr.setReportRequest(currentReport);
                        try {
                            mrrRepository.save(mrr);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("not working");
                }
            }
        }
        return ReportRequestMapper.toDto(currentReport);
    }

    public List<ApprovalDtoOutput> getApprovalsForReportRequest(long reportRequestId) {
        var approvals = approvalRepository.getApprovalsByReportRequestId(reportRequestId);
        List<ApprovalDtoOutput> approvalDtos = new ArrayList<>();
        approvals.stream()
                .map(ap -> new ApprovalDtoOutput(ap.getId(),ap.getReportRequest().getCrew().getFirstName(), ap.getApprovalTimestamp(),ap.getReportRequest().getId(),ap.getCrew().getLastName(),ap.getCrew().getCrewRank().toString()))
                .forEach(approvalDtos::add);

        return approvalDtos;
    }
}
