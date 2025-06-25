package org.mdt.crewtaskmanagement.mapper;

import org.mdt.crewtaskmanagement.dto.task.TaskDto;
import org.mdt.crewtaskmanagement.model.Task;
import org.mdt.crewtaskmanagement.model.system.Component;
import org.mdt.crewtaskmanagement.model.type.TaskInterval;


public class TaskMapper {

    public static Task fromTaskDto(TaskDto dto) {
        Task task = new Task();
        if (dto.getId() != 0L) {
            task.setId(dto.getId());
        }
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setPosition(dto.getPosition());
        task.setCritical(dto.isCritical());
        task.setTaskType(Enum.valueOf(TaskInterval.class, dto.getTaskType()));
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
        taskDto.setKind(task.getKind().toString());
        taskDto.setTaskType(task.getTaskType().toString());
        taskDto.setComponentName(task.getComponent().getComponentName());
        return taskDto;
    }
}
