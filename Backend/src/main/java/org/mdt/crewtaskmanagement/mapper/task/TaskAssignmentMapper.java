package org.mdt.crewtaskmanagement.mapper.task;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.taskschedule.TaskAssignmentDto;
import org.mdt.crewtaskmanagement.model.Crew;
import org.mdt.crewtaskmanagement.model.Ship;
import org.mdt.crewtaskmanagement.model.Task;
import org.mdt.crewtaskmanagement.model.TaskAssignment;
import org.mdt.crewtaskmanagement.repository.entity.CrewRepository;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
@RequiredArgsConstructor
public class TaskAssignmentMapper {
    private final CrewRepository crewRepository;

    public  TaskAssignment fromDto(TaskAssignmentDto dto, Task task, Crew crew,Crew reportTo, Ship ship) {
        if (dto == null) {
            return null;
        }
        TaskAssignment assignment = new TaskAssignment();
        assignment.setTask(task);
        assignment.setReportTo(reportTo);
        assignment.setCrew(crew);
        assignment.setShip(ship);
        assignment.setAssignedDate(parseDate(dto.getAssignDate()));
        assignment.setDeadlineDate(parseDate(dto.getDeadlineDate()));
        var reportToCrew = crewRepository.findById(dto.getReportToId()).orElse(null);
        assignment.setReportTo(reportToCrew);
        if (dto.getId() != 0L) {
            assignment.setId(dto.getId());
        }

        if (dto.getStatus() != null) {
            assignment.setStatus(TaskAssignment.AssignmentStatus.valueOf(dto.getStatus()));
        }

        return assignment;
    }

    public static TaskAssignmentDto toDto(TaskAssignment entity) {
        if (entity == null) {
            return null;
        }

        return TaskAssignmentDto.builder()
                .id(entity.getId())
                .taskId(entity.getTask() != null ? entity.getTask().getId() : 0L)
                .crewId(entity.getCrew() != null ? entity.getCrew().getId() : 0L)
                .shipId(entity.getShip() != null ? entity.getShip().getId() : 0L)
                .assignDate(formatDate(entity.getAssignedDate()))
                .deadlineDate(formatDate(entity.getDeadlineDate()))
                .status(entity.getStatus() != null ? entity.getStatus().name() : null)
                .build();
    }

    private static LocalDate parseDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateString, e);
        }
    }

    private static String formatDate(LocalDate date) {
        return date != null ? date.toString() : null;
    }
}
