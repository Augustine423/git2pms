package org.mdt.crewtaskmanagement.dto.approval;

import lombok.Data;

@Data
public class ApprovalDtoInput {
    private long id;
    private long crewId;
    private String approvedDate;
    private long reportRequestId;

    public ApprovalDtoInput(long id, long crewId, long reportRequestId) {
        this.id = id;
        this.crewId = crewId;
        this.reportRequestId = reportRequestId;
    }
}
