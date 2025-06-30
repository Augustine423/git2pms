package org.mdt.crewtaskmanagement.service;

import org.mdt.crewtaskmanagement.dto.task.CrewTaskDtoOutPut;
import org.mdt.crewtaskmanagement.dto.task.PageableTaskListDto;
import org.mdt.crewtaskmanagement.dto.task.TaskDto;
import org.mdt.crewtaskmanagement.output.PageResult;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    public TaskDto registerTask(TaskDto dto);
    public TaskDto updateTask(TaskDto dto);
    public TaskDto getTaskById(long id);
    public PageResult<TaskDto> getAllTasks(int page, int size);
    public void deleteTask(long id);
    public void assignTaskToCrew(long taskId, long crewId);
    public List<CrewTaskDtoOutPut> getTasksByCrewId(long crewId);


}
