package org.mdt.crewtaskmanagement.mapper.maintenanceLog;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.maintenancelog.MaintenanceLogDto;
import org.mdt.crewtaskmanagement.model.MaintenanceLog;
import org.mdt.crewtaskmanagement.repository.entity.TaskRepository;
import org.mdt.crewtaskmanagement.service.ICrewService;
import org.mdt.crewtaskmanagement.service.ITaskService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class MaintenanceLogMapper {

    private final ICrewService crewService;
    private final TaskRepository taskRepository;


    public MaintenanceLog fromDto(MaintenanceLogDto dto) {
        MaintenanceLog log = new MaintenanceLog();

        if (dto.getId() != null && dto.getId() != 0L) {
            log.setId(dto.getId());
        }

        log.setTask(taskRepository.findById(dto.getTaskId()).orElse(null));
        log.setDuty(crewService.getById(dto.getCrewId()));
        log.setRemarks(dto.getRemarks());

        log.setLastWork(dto.getLastWork() != null ? LocalDate.parse(dto.getLastWork()) : null);
        log.setNextDue(dto.getNextDue() != null ? LocalDate.parse(dto.getNextDue()) : null);

        // loggedAt will be set by @PrePersist
        return log;
    }

    public MaintenanceLogDto toDto(MaintenanceLog log) {
        MaintenanceLogDto dto = new MaintenanceLogDto();

        dto.setId(log.getId());
        dto.setTaskId(log.getTask() != null ? log.getTask().getId() : 0L);
        dto.setCrewId(log.getDuty().getId());
        dto.setCrewName(log.getDuty().getFirstName() + " " + log.getDuty().getLastName());
        dto.setLastWork(log.getLastWork() != null ? log.getLastWork().toString() : null);
        dto.setNextDue(log.getNextDue() != null ? log.getNextDue().toString() : null);
        dto.setRemarks(log.getRemarks());

        return dto;
    }
}
