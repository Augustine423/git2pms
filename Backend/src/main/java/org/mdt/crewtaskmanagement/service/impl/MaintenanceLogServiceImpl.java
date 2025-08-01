package org.mdt.crewtaskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.maintenancelog.MaintenanceLogDto;
import org.mdt.crewtaskmanagement.mapper.maintenanceLog.MaintenanceLogMapper;
import org.mdt.crewtaskmanagement.model.*;
import org.mdt.crewtaskmanagement.repository.entity.*;
import org.mdt.crewtaskmanagement.service.IMaintenanceLogService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private final TaskAssignmentRepository taskAssignmentRepository;
    private final ReportRequestRepository reportRequestRepository;
    private final MaintenanceLogMapper maintenanceLogMapper;

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
    @Transactional
    @Override
    public String finishTask(MaintenanceLogDto maintenanceLogDto) {
        System.out.println(maintenanceLogDto.getTaskAssignmentId()+ "tasiassignment id");
        TaskAssignment assignment = taskAssignmentRepository.findById(maintenanceLogDto.getTaskAssignmentId())
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        assignment.setStatus(TaskAssignment.AssignmentStatus.COMPLETED);
        taskAssignmentRepository.saveAndFlush(assignment);

        LocalDateTime nextDueDate = calculateNextDate(
                assignment.getDeadlineDate().atStartOfDay(),
                assignment.getTask().getIntervalValue(),
                assignment.getTask().getIntervalUnit()
        );
        maintenanceLogDto.setNextDue(nextDueDate.toString());

        Crew crew = crewRepository.findByEmail(maintenanceLogDto.getVerifyBy()).orElseThrow(() -> new RuntimeException("Crew not found who verify the report"));

        maintenanceLogRepository.save(maintenanceLogMapper.fromDto(maintenanceLogDto,assignment,crew));

        //log.setLastWork(LocalDate.now());
        // log.setRemarks(remark);
        // log.setStatus(MaintenanceLog.MaintenanceStatus.COMPLETED);

        //here can schedule next assignment or better make preSchedule Assignments
        // Schedule next assignment

        return "Task completed. Next task scheduled for " + nextDueDate.toLocalDate();
    }







}
