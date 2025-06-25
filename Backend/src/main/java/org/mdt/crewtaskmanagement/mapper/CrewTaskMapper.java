package org.mdt.crewtaskmanagement.mapper;

import org.mdt.crewtaskmanagement.dto.task.CrewTaskDtoOutPut;
import org.mdt.crewtaskmanagement.model.TaskAssignment;

public class CrewTaskMapper {

    public static CrewTaskDtoOutPut toDto(TaskAssignment entity) {
        if (entity == null) return null;

        return CrewTaskDtoOutPut.builder()
                .id((int) entity.getId())
                .crewId(entity.getCrew().getId())
                .crewFirstName(entity.getCrew().getFirstName())
                .crewLastName(entity.getCrew().getLastName())
                .shipName(entity.getShip().getName())
                .taskId(entity.getTask().getId())
                .taskTitle(entity.getTask().getTitle())
                .taskDescription(entity.getTask().getDescription())
                .assignedDate(entity.getAssignedDate().toString())
                .deadlineDate(entity.getDeadlineDate().toString())
                .completed(entity.isCompleted())
                .build();
    }
}
