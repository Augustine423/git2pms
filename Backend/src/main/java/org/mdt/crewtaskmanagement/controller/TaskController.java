package org.mdt.crewtaskmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.task.CrewTaskDtoOutPut;
import org.mdt.crewtaskmanagement.dto.task.PageableTaskListDto;
import org.mdt.crewtaskmanagement.dto.task.TaskDto;
import org.mdt.crewtaskmanagement.output.PageResult;
import org.mdt.crewtaskmanagement.service.MaintenanceLogService;
import org.mdt.crewtaskmanagement.service.impl.TaskServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mdt/task")
public class TaskController {
    private final TaskServiceImpl taskService;
    private final MaintenanceLogService maintenanceLogService;
    public record Remark(String remark) {}

    @PostMapping("/{assignmentId}/finish")
    public ResponseEntity<String> finishTask(@PathVariable Long assignmentId, @RequestBody Remark remark) {
        String res =  maintenanceLogService.finishTask(assignmentId, remark.remark);
        return ResponseEntity.ok(res);
    }


    @PostMapping("/register")
    public ResponseEntity<TaskDto> registerTask(@RequestBody TaskDto taskDto) {
        System.out.println(taskDto+ "taskDto");
        return ResponseEntity.ok(taskService.registerTask(taskDto));
    }

    @PostMapping("/update")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.updateTask(taskDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("/all")
    public PageResult<TaskDto> getAllTasks(
                                            @RequestParam(defaultValue = "0",required = false) int page,
                                            @RequestParam(defaultValue = "10",required = false) int size) {

        return taskService.getAllTasks(page,size);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable("id") long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Deleted task with id " + id);
    }
    @GetMapping("/assign-task-to-crew/{crewId}/{taskId}")
    public ResponseEntity<String> assignTaskToCrew(@PathVariable("taskId") long taskId, @PathVariable("crewId") long crewId) {
        taskService.assignTaskToCrew(taskId, crewId);
        return ResponseEntity.ok("Assigned task with id " + taskId + " to " + crewId);
    }
    @GetMapping("/get-task-by-crew-id/{crewId}")
    public ResponseEntity<List<CrewTaskDtoOutPut>> getCrewTaskById(@PathVariable("crewId") long crewId) {

        var tasks = taskService.getTasksByCrewId(crewId);
        return ResponseEntity.ok(tasks);
    }

}
