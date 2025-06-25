package org.mdt.crewtaskmanagement.dto.task;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class CrewTaskDtoOutPut {
    private int id;
    private long crewId;
    private String crewFirstName;
    private String crewLastName;
    private String shipName;
    private long taskId;
    private String taskTitle;
    private String taskDescription;
    private String category;
    private String assignedDate;
    private String deadlineDate;
    private boolean completed;

}
