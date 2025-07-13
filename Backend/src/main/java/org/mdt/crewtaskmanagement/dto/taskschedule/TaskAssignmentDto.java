package org.mdt.crewtaskmanagement.dto.taskschedule;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskAssignmentDto {
    private long id;
    private long taskId;
    private long crewId;
    private long shipId;
    private long reportToId;
    private String assignDate;
    private String deadlineDate;
    private String status;

}
