package org.mdt.crewtaskmanagement.service;

import org.mdt.crewtaskmanagement.dto.task.CrewTaskDtoOutPut;
import org.mdt.crewtaskmanagement.dto.task.TaskDto;
import org.mdt.crewtaskmanagement.model.Task;
import org.mdt.crewtaskmanagement.model.TaskAssignment;
import org.mdt.crewtaskmanagement.output.PageResult;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITaskService {
    TaskDto registerTask(TaskDto dto);
    TaskDto updateTask(TaskDto dto);
    TaskDto getTaskById(long id);
    Task getById(long id);
    TaskAssignment getTaskAssignmentById(long id);
    PageResult<CrewTaskDtoOutPut> getTasksByCrewId(long crewId,int page,int size);
    PageResult<TaskDto> getAllTasks(int page, int size);
    String deleteTask(long id);
    String assignTaskToCrew(long taskId, long crewId);
    PageResult<CrewTaskDtoOutPut> getUnfinishedTasksByCrewId(long crewId, int page, int size);
    PageResult<CrewTaskDtoOutPut> getUnfinishedTasksCrossedDeadlineByCrewId(long crewId, int page, int size);
    PageResult<CrewTaskDtoOutPut> getFinishedTasksByCrewId(long crewId, int page, int size);

}
