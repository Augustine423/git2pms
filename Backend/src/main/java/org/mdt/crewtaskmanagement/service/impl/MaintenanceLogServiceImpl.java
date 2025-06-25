package org.mdt.crewtaskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.maintenancelog.MaintenanceLogDto;
import org.mdt.crewtaskmanagement.model.*;
import org.mdt.crewtaskmanagement.model.type.TaskInterval;
import org.mdt.crewtaskmanagement.repository.entity.*;
import org.mdt.crewtaskmanagement.service.MaintenanceLogService;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceLogServiceImpl implements MaintenanceLogService {
    private final MaintenanceLogRepository maintenanceLogRepository;
    private final CrewRepository crewRepository;
    private final TaskRepository taskRepository;
    private final TaskScheduleRepository taskScheduleRepository;
    private final TaskAssignmentRepository taskAssignmentRepository;
    private final ReportRequestRepository reportRequestRepository;
    @Override
    public void createMaintenanceLog(MaintenanceLogDto dto) {
        MaintenanceLog maintenanceLog = new MaintenanceLog();
        maintenanceLog.setId(dto.getId());
        Task task = taskRepository.findById(dto.getTaskId()).orElseThrow();
        maintenanceLog.setTask(task);
        maintenanceLog.setDuty(crewRepository.findById(dto.getCrewId()).orElseThrow());
        maintenanceLog.setLastWork(LocalDate.now());
        maintenanceLog.setNextDue(getNextDueByTaskType(task.getTaskType().toString()));
        maintenanceLog.setRemark(dto.getRemarks());

        maintenanceLogRepository.save(maintenanceLog);
    }



    public LocalDate getNextDueByTaskType(String taskType) {
        String interval = taskType.toLowerCase();
        LocalDate today = LocalDate.now();
        LocalDate nextDue;

        switch (interval) {
            case "weekly":
                nextDue = today.plusWeeks(1);
                break;
            case "monthly":
                nextDue = today.plusMonths(1);
                break;
            case "quarterly":
                nextDue = today.plusMonths(3);
                break;
            case "semi_annual":
            case "semiannual":
                nextDue = today.plusMonths(6);
                break;
            case "annual":
            case "yearly":
                nextDue = today.plusYears(1);
                break;
            default:
                throw new IllegalArgumentException("Unknown task interval: " + interval);
        }

        return nextDue;
    }


    @Scheduled(cron = "0 0 1 * * ?")
    public void scheduleTasks(){
        List<TaskSchedule> schedules = taskScheduleRepository.findByNextDueBefore(LocalDate.now()).orElseThrow();
                for (TaskSchedule schedule : schedules) {
        TaskAssignment assignment = new TaskAssignment();
        assignment.setTask(schedule.getTask());
        assignment.setAssignedDate(LocalDate.now());
        assignment.setDeadlineDate(schedule.getNextDue());
        taskAssignmentRepository.save(assignment);
        schedule.setNextDue(schedule.getNextDue().plusMonths(1));
        taskScheduleRepository.save(schedule);
    }

    }

    //__________________________________________________________________________________
    @Override
    public String finishTask(@PathVariable Long assignmentId, @RequestBody(required = false) String remark) {
        TaskAssignment assignment = taskAssignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        ReportRequest report = reportRequestRepository.findByTaskAssignmentId(assignmentId);



        // Mark assignment as completed
        assignment.setCompleted(true);
        taskAssignmentRepository.save(assignment);

        // Create maintenance log
        MaintenanceLog log = new MaintenanceLog();
        log.setTask(assignment.getTask());
        log.setDuty(assignment.getCrew());
        log.setLastWork(LocalDate.now());
        log.setNextDue(LocalDate.now().plusMonths(1));
        log.setRemark(remark);
        log.setStatus(MaintenanceLog.MaintenanceStatus.COMPLETED);
        maintenanceLogRepository.save(log);

        // Schedule next task
        if (assignment.getTask().getTaskType() == TaskInterval.Monthly) {
            TaskAssignment next = new TaskAssignment();
            next.setTask(assignment.getTask());
            next.setCrew(assignment.getCrew());
            next.setShip(assignment.getShip());
            next.setAssignedDate(LocalDate.now());
            next.setDeadlineDate(getNextDueByTaskType(assignment.getTask().getTaskType().toString()));
            taskAssignmentRepository.save(next);
        }

        return "Task completed. Next task scheduled for " + log.getNextDue();
    }


}
