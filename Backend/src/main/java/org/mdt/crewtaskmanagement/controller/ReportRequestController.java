package org.mdt.crewtaskmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.approval.ApprovalDtoInput;
import org.mdt.crewtaskmanagement.dto.approval.ApprovalDtoOutput;
import org.mdt.crewtaskmanagement.dto.material.MaterialDto;
import org.mdt.crewtaskmanagement.dto.reportrequest.ReportRequestDto;
import org.mdt.crewtaskmanagement.exception.ServiceBaseException;
import org.mdt.crewtaskmanagement.output.PageResult;
import org.mdt.crewtaskmanagement.service.ICrewService;
import org.mdt.crewtaskmanagement.service.impl.ReportRequestServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mdt/report")
public class ReportRequestController {
    private final ReportRequestServiceImpl reportRequestService;
    private final ICrewService crewService ;



    @PostMapping("/create")
    public ResponseEntity<ReportRequestDto> createReportRequest(@RequestBody ReportRequestDto reportRequestDto) throws ServiceBaseException {
        return ResponseEntity.ok(reportRequestService.createReportWithMaterials(reportRequestDto));
    }
// i think no need this., i should combine
    @GetMapping("/get-materials/{requestReportId}")
    public ResponseEntity<List<MaterialDto>> getMaterialsFromReportRequest(@PathVariable Long requestReportId) {
        return ResponseEntity.ok(reportRequestService.getMaterialsFromReportRequest(requestReportId));
    }

    //add approval
    @PostMapping("/add-approval")
    public ResponseEntity<String> addApprovalToReportRequest(@RequestBody ApprovalDtoInput approvalDtoInput)throws Exception {
        reportRequestService.addApprovalForReportRequest(approvalDtoInput);
        return ResponseEntity.ok("Approved by ");

    }
    //normally i don't think need this as an API
    @GetMapping("/all")
    public PageResult<ReportRequestDto> getAllReportRequests(@RequestParam(defaultValue = "0",required = false) int page,
                                                                       @RequestParam(defaultValue = "10",required = false) int size) {
        return reportRequestService.getAllReportRequests(page,size);
    }

    //get as an leader
    @GetMapping("/get-report-request-to-approve")
    public PageResult<ReportRequestDto> getReportRequestsToApprove(@RequestParam(defaultValue = "0",required = false) int page,
                                                             @RequestParam(defaultValue = "10",required = false) int size
    ) throws ServiceBaseException {
        var crew = crewService.getIdFromContext();
        return reportRequestService.getReportRequestsToApprove(crew,page,size);
    }

    //get approved report-requests by crewId
    @GetMapping("/get-approved-report-requests-as-crew")
    public PageResult<ReportRequestDto> getApprovedReportRequests(@RequestParam(defaultValue = "0",required = false) int page,
                                                                  @RequestParam(defaultValue = "10",required = false) int size) throws ServiceBaseException {
        long crewId = crewService.getIdFromContext();
        return reportRequestService.getApprovedReportRequestAsCrew(crewId,page,size);
    }
//get reports approved by leader from those who ever worked under him
    //@PreAuthorize("hasRole('ROLE_LEADER')")
    @GetMapping("/get-approved-report-requests-for-leader")
    public PageResult<ReportRequestDto> getApprovedReportRequestsAsLeader(@RequestParam(defaultValue = "0",required = false) int page,
                                                                            @RequestParam(defaultValue = "10",required = false) int size) throws ServiceBaseException {
        long crewId = crewService.getIdFromContext();
        return reportRequestService.getApprovedReportRequestForLeader(crewId,page,size);
    }

    @GetMapping("/get-pending-reports/{crewId}")
    public PageResult<ReportRequestDto> getAllReportRequests(@PathVariable Long crewId,
                                                       @RequestParam(defaultValue = "0",required = false) int page,
                                                       @RequestParam(defaultValue = "10",required = false) int size) {
        return reportRequestService.getReportRequestsWaitingApprove(crewId,page,size);
    }

    @GetMapping("/get-approvals/{reportRequestId}")
    public List<ApprovalDtoOutput> getAllApprovalsFromReportRequestById(@PathVariable Long reportRequestId) {
        return reportRequestService.getApprovalsForReportRequest(reportRequestId);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReportRequestDto> getReportRequestById(@PathVariable("id") Long reportRequestId) {
        return ResponseEntity.ok(reportRequestService.getReportRequestById(reportRequestId));
    }
    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<String> deleteReportRequestById(@PathVariable Long reportRequestId) {
        reportRequestService.cancelReportRequestById(reportRequestId);
        return ResponseEntity.ok("Report request deleted");
    }

}
