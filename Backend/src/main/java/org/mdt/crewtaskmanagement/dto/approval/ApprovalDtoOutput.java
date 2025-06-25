package org.mdt.crewtaskmanagement.dto.approval;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApprovalDtoOutput {
    private long id;
    private String crewName;
    private String approvedDate;
    private long reportRequestId;
    private String approvedBy;
    private String position;

    public ApprovalDtoOutput(long id, String crewName, LocalDateTime approvedDate, long reportRequestId, String approvedBy, String position) {
        this.id = id;
        this.crewName = crewName;
        this.approvedDate = approvedDate.toString();
        this.reportRequestId = reportRequestId;
        this.approvedBy = approvedBy;
        this.position = position;
    }
}
