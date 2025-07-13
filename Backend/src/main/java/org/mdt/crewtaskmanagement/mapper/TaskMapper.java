package org.mdt.crewtaskmanagement.mapper;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.task.TaskDto;
import org.mdt.crewtaskmanagement.model.Task;
import org.mdt.crewtaskmanagement.service.ICompanyService;
import org.mdt.crewtaskmanagement.service.IComponentService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
@Component
@RequiredArgsConstructor
public class TaskMapper {
    private final IComponentService componentService;

    public  Task fromTaskDto(TaskDto dto) {
        Task task = new Task();
        if (dto.getId() != 0L) {
            task.setId(dto.getId());
        }
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setPosition(dto.getPosition());
        task.setCritical(dto.isCritical());
        task.setKind(Enum.valueOf(Task.TaskKind.class, dto.getKind()));
        return task;
    }

    public static TaskDto toTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setPosition(task.getPosition());
        taskDto.setCritical(task.isCritical());
        taskDto.setIntervalValue(task.getIntervalValue());
        taskDto.setIntervalUnit(task.getIntervalUnit().toString());
        taskDto.setKind(task.getKind().toString());
        return taskDto;
    }
}
