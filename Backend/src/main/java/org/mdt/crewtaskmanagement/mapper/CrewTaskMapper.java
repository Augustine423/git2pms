package org.mdt.crewtaskmanagement.mapper;

import org.mdt.crewtaskmanagement.dto.task.CrewTaskDtoOutPut;
import org.mdt.crewtaskmanagement.exception.ServiceBaseException;
import org.mdt.crewtaskmanagement.model.Crew;
import org.mdt.crewtaskmanagement.model.TaskAssignment;

import java.util.List;

public class CrewTaskMapper {

    public static CrewTaskDtoOutPut toDto(TaskAssignment entity) throws ServiceBaseException {
        if (entity != null) {
            Crew manager = entity.getReportTo(); // get the reportTo (manager)

            String managerName = (manager != null)
                    ? (safe(manager.getFirstName()) + " " + safe(manager.getLastName())).trim()
                    : "";

            return new CrewTaskDtoOutPut(
                    entity.getId(),
                    entity.getCrew().getId(),
                    entity.getShip().getName(),
                    entity.getTask().getId(),
                    entity.getTask().getTitle(),
                    entity.getTask().getDescription(),
                    entity.getTask().getIntervalValue(),
                    entity.getTask().getIntervalUnit(),
                    entity.getAssignedDate(),
                    entity.getDeadlineDate(),
                    entity.getStatus(),
                    managerName
            );
        }
        throw new ServiceBaseException(List.of("TaskAssignment is null"));
    }

    private static String safe(String value) {
        return value != null ? value : "";
    }
}
