package org.mdt.crewtaskmanagement.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mdt.crewtaskmanagement.model.Task;
import org.mdt.crewtaskmanagement.model.TaskAssignment;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrewTaskDtoOutPut {

    private long assignmentId;
    private long crewId;
    private String shipName;
    private long taskId;
    private String taskTitle;
    private String taskDescription;
    private int intervalValue;
    private Task.IntervalUnit intervalUnit;
    private LocalDate assignedDate;
    private LocalDate deadlineDate;
    private TaskAssignment.AssignmentStatus status;
    private String managerName;
}
