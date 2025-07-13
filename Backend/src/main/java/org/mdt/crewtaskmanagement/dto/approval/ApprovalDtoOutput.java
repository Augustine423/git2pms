package org.mdt.crewtaskmanagement.dto.approval;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApprovalDtoOutput {
    private long id;
    private String approver;
    private String approvedDate;
    private long reportRequestId;
    private String approvedBy;
    private String rank;


}
