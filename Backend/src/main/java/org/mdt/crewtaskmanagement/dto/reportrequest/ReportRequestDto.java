package org.mdt.crewtaskmanagement.dto.reportrequest;

import lombok.Builder;
import lombok.Data;
import org.mdt.crewtaskmanagement.dto.approval.ApprovalDtoOutput;

import java.util.List;

@Data
@Builder
public class ReportRequestDto {
    private long id;
    private String title;
    private long taskAssignmentId;
    private String content; //user enter
    private String requestDate;
    private List<MaterialRequestDto> requestedMaterials;
    private List<ApprovalDtoOutput> approvals;
    private String status;
    //user enter

}
