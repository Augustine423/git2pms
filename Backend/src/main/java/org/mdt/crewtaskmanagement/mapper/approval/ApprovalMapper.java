package org.mdt.crewtaskmanagement.mapper.approval;

import org.mdt.crewtaskmanagement.dto.approval.ApprovalDtoInput;
import org.mdt.crewtaskmanagement.dto.approval.ApprovalDtoOutput;
import org.mdt.crewtaskmanagement.model.Approval;
import org.mdt.crewtaskmanagement.model.Crew;
import org.mdt.crewtaskmanagement.model.ReportRequest;
import org.mdt.crewtaskmanagement.model.type.CrewRank;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApprovalMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static ApprovalDtoOutput toDto(Approval approval) {
        return ApprovalDtoOutput.builder()
                .id(approval.getId())
                .approver(approval.getApprover() != null ? approval.getApprover().getFirstName() + " " + approval.getApprover().getLastName() : null)
                .approvedBy(approval.getApprover() != null ? approval.getApprover().getEmail() : null)
                .approvedDate(approval.getApprovalTimestamp() != null ? approval.getApprovalTimestamp().format(FORMATTER) : null)
                .reportRequestId(approval.getReportRequest() != null ? approval.getReportRequest().getId() : 0L)
                .rank(approval.getRank() != null ? approval.getRank().name() : null)
                .build();
    }

    public static Approval fromDto(ApprovalDtoInput dto, Crew approver, ReportRequest reportRequest) {
        Approval approval = new Approval();
        approval.setId(dto.getId());
        approval.setApprover(approver);
        approval.setReportRequest(reportRequest);
        approval.setApprovalTimestamp(dto.getApprovedDate() != null
                ? LocalDateTime.parse(dto.getApprovedDate(), FORMATTER)
                : LocalDateTime.now());
        approval.setRank(approver.getCrewRank() != null ? approver.getCrewRank() : CrewRank.OTHER); // default fallback
        return approval;
    }
}
