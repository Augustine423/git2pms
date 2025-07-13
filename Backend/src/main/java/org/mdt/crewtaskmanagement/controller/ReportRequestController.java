package org.mdt.crewtaskmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.approval.ApprovalDtoInput;
import org.mdt.crewtaskmanagement.dto.approval.ApprovalDtoOutput;
import org.mdt.crewtaskmanagement.dto.material.MaterialDto;
import org.mdt.crewtaskmanagement.dto.reportrequest.ReportRequestDto;
import org.mdt.crewtaskmanagement.output.PageResult;
import org.mdt.crewtaskmanagement.service.impl.ReportRequestServiceImpl;
import org.springframework.data.domain.Page;
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

    @GetMapping("/get-materials/{requestReportId}")
    public ResponseEntity<List<MaterialDto>> getMaterialsFromReportRequest(@PathVariable Long requestReportId) {
        return ResponseEntity.ok(reportRequestService.getMaterialsFromReportRequest(requestReportId));
    }

    @PostMapping("/add-approval")
    public ResponseEntity<String> addApprovalToReportRequest(@RequestBody ApprovalDtoInput approvalDtoInput)throws Exception {
        reportRequestService.addApprovalForReportRequest(approvalDtoInput);
        return ResponseEntity.ok("Approved by ");

    }

    @GetMapping("/all")
    public PageResult<ReportRequestDto> getAllReportRequests(@RequestParam(defaultValue = "0",required = false) int page,
                                                                       @RequestParam(defaultValue = "10",required = false) int size
    ) {
        return reportRequestService.getAllReportRequests(page,size);
    }
//
//    @GetMapping("/get-approved-report-requests/{crewId}")
//    public PageResult<ReportRequestDto> getApprovedReportRequests(@PathVariable Long crewId,
//                                                                  @RequestParam(defaultValue = "0",required = false) int page,
//                                                                  @RequestParam(defaultValue = "10",required = false) int size) {
//        return reportRequestService.getApprovedReportRequest(crewId,page,size);
//    }

//    @GetMapping("/get-pending-reports/{crewId}")
//    public PageResult<ReportRequestDto> getAllReportRequests(@PathVariable Long crewId,
//                                                       @RequestParam(defaultValue = "0",required = false) int page,
//                                                       @RequestParam(defaultValue = "10",required = false) int size) {
//        return reportRequestService.getPendingReportRequests(crewId,page,size);
//    }

    @GetMapping("/get-approvals/{reportRequestId}")
    public List<ApprovalDtoOutput> getAllApprovalsFromReportRequestById(@PathVariable Long reportRequestId) {
        return reportRequestService.getApprovalsForReportRequest(reportRequestId);
    }

}
