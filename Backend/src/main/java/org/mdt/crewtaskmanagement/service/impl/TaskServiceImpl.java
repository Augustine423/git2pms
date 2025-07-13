package org.mdt.crewtaskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.task.CrewTaskDtoOutPut;
import org.mdt.crewtaskmanagement.dto.task.TaskDto;
import org.mdt.crewtaskmanagement.mapper.TaskMapper;
import org.mdt.crewtaskmanagement.model.Crew;
import org.mdt.crewtaskmanagement.model.Ship;
import org.mdt.crewtaskmanagement.model.Task;
import org.mdt.crewtaskmanagement.model.TaskAssignment;
import org.mdt.crewtaskmanagement.model.system.Component;
import org.mdt.crewtaskmanagement.output.PageResult;
import org.mdt.crewtaskmanagement.repository.entity.*;
import org.mdt.crewtaskmanagement.service.IMaintenanceLogService;
import org.mdt.crewtaskmanagement.service.ITaskService;
import org.mdt.crewtaskmanagement.utils.GetEntitesWithPageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService {
    private final TaskRepository taskRepository;
    private final CrewRepository crewRepository;
    private final TaskAssignmentRepository tsrepo;
    private final ComponentRepositoryOne componentRepository;
    private final CrewAssignmentRepository crewAssignmentRepository;
    private final IMaintenanceLogService maintenanceLogService;
    private final TaskAssignmentRepository taskAssignmentRepository;
    private final TaskMapper taskMapper;


    @Override
    public PageResult<CrewTaskDtoOutPut> getTasksByCrewId(long crewId,int page,int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("deadlineDate"));
        var dtos =  tsrepo.findAllTasksByCrewId(crewId,pageable);
        return GetEntitesWithPageable.getAllWithPageable(pageable,dtos,null);
    }

    @Override
    public PageResult<CrewTaskDtoOutPut> getUnfinishedTasksByCrewId(long crewId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("deadlineDate"));
        Page<CrewTaskDtoOutPut> dtos =  tsrepo.findUncompletedTasksByCrewId(crewId,pageable);
        return GetEntitesWithPageable.getAllWithPageable(pageable,dtos,null);
    }

    @Override
    public PageResult<CrewTaskDtoOutPut> getUnfinishedTasksCrossedDeadlineByCrewId(long crewId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("deadlineDate"));
        Page<CrewTaskDtoOutPut> dtos =  tsrepo.findOverdueTasksByCrewId(crewId,LocalDate.now(),pageable);
        return GetEntitesWithPageable.getAllWithPageable(pageable,dtos,null);
    }

    @Override
    public PageResult<CrewTaskDtoOutPut> getFinishedTasksByCrewId(long crewId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("deadlineDate"));
        Page<CrewTaskDtoOutPut> dtos =  tsrepo.findCompletedTasksByCrewId(crewId,pageable);
        return GetEntitesWithPageable.getAllWithPageable(pageable,dtos,null);
    }


    @Override
    public String deleteTask(long id) {
         taskRepository.deleteById(id);
         return "Task with id " + id + " deleted";

    }

    @Override
    public String assignTaskToCrew(long taskId, long crewId) {

        Task task;
        task = taskRepository.findById(taskId).orElseThrow();
        Crew crew = crewRepository.findById(crewId).orElseThrow();
        Ship ship = crewAssignmentRepository.getShipByCrewId(crewId).orElseThrow();
        TaskAssignment ta = new TaskAssignment();
        LocalDateTime nextDueDate = maintenanceLogService.calculateNextDate(
                LocalDateTime.now(),
                task.getIntervalValue(),
                task.getIntervalUnit()
        );
        ta.setTask(task);
        ta.setCrew(crew);
        ta.setShip(ship);
        ta.setReportTo(crewAssignmentRepository.findCrewReportTo(crewId).orElseThrow());
        ta.setStatus(TaskAssignment.AssignmentStatus.UPCOMING);
        ta.setAssignedDate(LocalDate.now());
        ta.setDeadlineDate(!task.getMaintenanceLogs().isEmpty() ? task.getMaintenanceLogs().getLast().getNextDue() : nextDueDate.toLocalDate());
        tsrepo.save(ta);
        return   "Assigned task with id " + taskId + " to " + crewId;
    }

    public TaskDto registerTask(TaskDto dto){
        Task task = taskMapper.fromTaskDto(dto);
        Component component = componentRepository.findByComponentName(dto.getComponentName()).orElseThrow();
        task.setComponent(component);
        return TaskMapper.toTaskDto(taskRepository.save(task));
    }

    public TaskDto updateTask(TaskDto dto){
        Task task = taskMapper.fromTaskDto(dto);
        task.setId(dto.getId());
        taskRepository.save(task);
        return TaskMapper.toTaskDto(task);
    }

    public TaskDto getTaskById(long id){
        return TaskMapper.toTaskDto(taskRepository.findById(id).orElseThrow());
    }

    public Task getById(long id){
        return taskRepository.findById(id).orElseThrow();
    }

    public TaskAssignment getTaskAssignmentById(long id){
        return taskAssignmentRepository.findById(id).orElseThrow();
    }


    public PageResult<TaskDto> getAllTasks(int page, int size){
        var pageable = PageRequest.of(page, size);
        Page<Task> tasks = taskRepository.findAll(pageable);
        return GetEntitesWithPageable.getAllWithPageable(pageable,tasks, TaskMapper::toTaskDto);
    }
}
