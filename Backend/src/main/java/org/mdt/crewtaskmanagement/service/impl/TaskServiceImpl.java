package org.mdt.crewtaskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.task.CrewTaskDtoOutPut;
import org.mdt.crewtaskmanagement.dto.task.PageableTaskListDto;
import org.mdt.crewtaskmanagement.dto.task.TaskDto;
import org.mdt.crewtaskmanagement.mapper.CrewTaskMapper;
import org.mdt.crewtaskmanagement.mapper.TaskMapper;
import org.mdt.crewtaskmanagement.model.Crew;
import org.mdt.crewtaskmanagement.model.Ship;
import org.mdt.crewtaskmanagement.model.Task;
import org.mdt.crewtaskmanagement.model.TaskAssignment;
import org.mdt.crewtaskmanagement.model.system.Component;
import org.mdt.crewtaskmanagement.output.PageResult;
import org.mdt.crewtaskmanagement.repository.entity.*;
import org.mdt.crewtaskmanagement.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final CrewRepository crewRepository;
    private final ShipRepository shipRepository;
    private final TaskAssignmentRepository tsrepo;
    private final ComponentRepositoryOne componentRepository;

    @Override
    public List<CrewTaskDtoOutPut> getTasksByCrewId(long crewId) {
        List<TaskAssignment> assignments = tsrepo.findByCrewIdWithDetails(crewId);
        return assignments.stream()
                .sorted(Comparator.comparing(TaskAssignment::getDeadlineDate))
                .map(CrewTaskMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteTask(long id) {
        taskRepository.deleteById(id);

    }

    @Override
    public void assignTaskToCrew(long taskId, long crewId) {
        Task task = taskRepository.findById(taskId).get();
        Crew crew = crewRepository.findById(crewId).get();
        Ship ship = shipRepository.findById(1L).get();
        TaskAssignment ta = new TaskAssignment();
        ta.setTask(task);
        ta.setCrew(crew);
        ta.setShip(ship);
        ta.setAssignedDate(LocalDate.now());
        ta.setDeadlineDate(LocalDate.now());
        tsrepo.save(ta);


    }

    public TaskDto registerTask(TaskDto dto){
        Task task = TaskMapper.fromTaskDto(dto);
        Component component = componentRepository.findByComponentName(dto.getComponentName()).orElseThrow();
        task.setComponent(component);
        return TaskMapper.toTaskDto(taskRepository.save(task));
    }

    public TaskDto updateTask(TaskDto dto){
        Task task = TaskMapper.fromTaskDto(dto);
        task.setId(dto.getId());
        taskRepository.save(task);
        return TaskMapper.toTaskDto(task);
    }

    public TaskDto getTaskById(long id){
        return TaskMapper.toTaskDto(taskRepository.findById(id).orElseThrow());
    }

    public PageResult<TaskDto> getAllTasks(int page, int size){
        var pageable = PageRequest.of(page, size);
        Page<Task> tasks = taskRepository.findAll(pageable);


        //  Map to DTO
        List<TaskDto> dtoList = tasks.stream()
                .map(task -> TaskMapper.toTaskDto(task))
                .toList();

        return new PageResult<TaskDto>(
                dtoList,
                tasks.getTotalElements(),
                size,
                page
        );
        //.stream().map(TaskMapper::toTaskDto).collect(Collectors.toList());
    }



}
