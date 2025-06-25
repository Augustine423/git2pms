package org.mdt.crewtaskmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.approval.ApprovalDtoInput;
import org.mdt.crewtaskmanagement.dto.approval.ApprovalDtoOutput;
import org.mdt.crewtaskmanagement.dto.material.MaterialDto;
import org.mdt.crewtaskmanagement.dto.reportrequest.ReportRequestDto;
import org.mdt.crewtaskmanagement.service.impl.ReportRequestServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mdt/report")
public class ReportRequestController {
    private final ReportRequestServiceImpl reportRequestService;


    @PostMapping("/create")
    public ResponseEntity<ReportRequestDto> createReportRequest(@RequestBody ReportRequestDto reportRequestDto) {
        return ResponseEntity.ok(reportRequestService.createReportWithMaterials(reportRequestDto));

    }

    //    @GetMapping("/add-materials/{requestReportId}/{materialId}/{quantity}")
//    public String addMaterials(@PathVariable Long requestReportId, @PathVariable Long materialId, @PathVariable int quantity) {
//         return reportRequestService.assignMaterialsToReportRequest(requestReportId, materialId, quantity);
//
//    }
    @GetMapping("/get-materials/{requestReportId}")
    public ResponseEntity<List<MaterialDto>> getAllMaterials(@PathVariable Long requestReportId) {
        List<MaterialDto> m=  reportRequestService.getMaterialsFromReportRequest(requestReportId);
        return ResponseEntity.ok(m);
}
    @PostMapping("/add-approval")
    public ResponseEntity<String> addApprovalToReportRequest(@RequestBody ApprovalDtoInput approvalDtoInput) {
        reportRequestService.addApprovalForReportRequest(approvalDtoInput);
        return ResponseEntity.ok("Approved by ");

    }

    @GetMapping("/get-approved-report-requests/{crewId}")
    public List<ReportRequestDto> getApprovedReportRequests(@PathVariable Long crewId) {
        return reportRequestService.getApprovedReportRequest(crewId);
    }

    @GetMapping("/get-pendings/{crewId}")
    public List<ReportRequestDto> getAllReportRequests(@PathVariable Long crewId) {
        return reportRequestService.getPendingReportRequests(crewId);
    }
    @GetMapping("/get-approvals/{reportRequestId}")
    public List<ApprovalDtoOutput> getAllApprovalsFromReportRequestById(@PathVariable Long reportRequestId) {
        return reportRequestService.getApprovalsForReportRequest(reportRequestId);
    }
}
