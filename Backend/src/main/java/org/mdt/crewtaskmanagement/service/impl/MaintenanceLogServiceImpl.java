package org.mdt.crewtaskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.maintenancelog.MaintenanceLogDto;
import org.mdt.crewtaskmanagement.mapper.maintenanceLog.MaintenanceLogMapper;
import org.mdt.crewtaskmanagement.model.*;
import org.mdt.crewtaskmanagement.repository.entity.*;
import org.mdt.crewtaskmanagement.service.IMaintenanceLogService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceLogServiceImpl implements IMaintenanceLogService {
    private final MaintenanceLogRepository maintenanceLogRepository;
    private final CrewRepository crewRepository;
    private final TaskRepository taskRepository;
    private final TaskScheduleRepository taskScheduleRepository;
    private final TaskAssignmentRepository taskAssignmentRepository;
    private final ReportRequestRepository reportRequestRepository;
    private final MaintenanceLogMapper maintenanceLogMapper;
    @Override
    public void createMaintenanceLog(MaintenanceLogDto dto) {
        MaintenanceLog maintenanceLog = maintenanceLogMapper.fromDto(dto);
        maintenanceLogRepository.save(maintenanceLog);
    }


    @Override
    public LocalDateTime calculateNextDate(LocalDateTime from, int interval, Task.IntervalUnit unit) {
        return switch (unit) {
            case DAYS -> from.plusDays(interval);
            case WEEKS -> from.plusWeeks(interval);
            case MONTHS -> from.plusMonths(interval);
            case YEARS -> from.plusYears(interval);
            case HOURS -> from.plusHours(interval);
        };
    }






    //__________________________________________________________________________________
    @Override
    public String finishTask(Long assignmentId, String remark) {
        TaskAssignment assignment = taskAssignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        // Mark assignment as completed
        assignment.setStatus(TaskAssignment.AssignmentStatus.COMPLETED);
        taskAssignmentRepository.save(assignment);

        // Create maintenance log
        MaintenanceLog log = new MaintenanceLog();
        log.setTask(assignment.getTask());
        log.setDuty(assignment.getCrew());
        log.setLastWork(LocalDate.now());
        log.setRemarks(remark);
        log.setStatus(MaintenanceLog.MaintenanceStatus.COMPLETED);

        // Calculate next due date
        LocalDateTime nextDueDate = calculateNextDate(
                assignment.getDeadlineDate().atStartOfDay(),
                assignment.getTask().getIntervalValue(),
                assignment.getTask().getIntervalUnit()
        );
        log.setNextDue(nextDueDate.toLocalDate());
        maintenanceLogRepository.save(log);

        // Schedule next assignment
        TaskAssignment next = new TaskAssignment();
        next.setTask(assignment.getTask());
        next.setCrew(assignment.getCrew());
        next.setShip(assignment.getShip());
        next.setAssignedDate(LocalDate.now());
        next.setDeadlineDate(nextDueDate.toLocalDate());  // safely convert to LocalDate
        next.setStatus(TaskAssignment.AssignmentStatus.UPCOMING);


        taskAssignmentRepository.save(next);

        return "Task completed. Next task scheduled for " + nextDueDate.toLocalDate();
    }






}
