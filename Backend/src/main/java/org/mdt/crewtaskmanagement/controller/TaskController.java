package org.mdt.crewtaskmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.maintenancelog.MaintenanceLogDto;
import org.mdt.crewtaskmanagement.dto.task.CrewTaskDtoOutPut;
import org.mdt.crewtaskmanagement.dto.task.TaskDto;
import org.mdt.crewtaskmanagement.exception.ServiceBaseException;
import org.mdt.crewtaskmanagement.mapper.TaskMapper;
import org.mdt.crewtaskmanagement.output.PageResult;
import org.mdt.crewtaskmanagement.service.IMaintenanceLogService;
import org.mdt.crewtaskmanagement.service.impl.CrewServiceImpl;
import org.mdt.crewtaskmanagement.service.impl.TaskServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mdt/task")
public class TaskController {
    private final TaskServiceImpl taskService;
    private final IMaintenanceLogService maintenanceLogService;
    private final CrewServiceImpl crewService;

    public record Remark(String remark) {}
//will implement ..
//    @PostMapping("/{assignmentId}/finish")
//    public ResponseEntity<String> finishTask(@PathVariable Long assignmentId, @RequestBody() Remark remark) {
//        return ResponseEntity.ok(maintenanceLogService.finishTask(assignmentId, );
//    }

    @PostMapping("/register")
    public ResponseEntity<TaskDto> registerTask(@RequestBody TaskDto taskDto) {
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
    public PageResult<TaskDto> getAllTasks(@RequestParam(defaultValue = "0",required = false) int page,
                                           @RequestParam(defaultValue = "10",required = false) int size) {
        return taskService.getAllTasks(page,size);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable("id") long id) {
        return ResponseEntity.ok(taskService.deleteTask(id));
    }

    @GetMapping("/assign-task-to-crew/{crewId}/{taskId}")
    public ResponseEntity<String> assignTaskToCrew(@PathVariable("taskId") long taskId, @PathVariable("crewId") long crewId) {
        return ResponseEntity.ok(taskService.assignTaskToCrew(taskId, crewId));
    }

    @GetMapping("/get-task-by-crew-id")
    public ResponseEntity<PageResult<CrewTaskDtoOutPut>> getCrewTaskById(@RequestParam(value = "crewId", required = false) Long crewIdParam,
                                                                         @RequestHeader(value = "crewId", required = false) Long crewIdHeader,
                                                                         @RequestParam(defaultValue = "0",required = false) int page,
                                                                         @RequestParam(defaultValue = "10",required = false) int size) throws ServiceBaseException {
        Long finalCrewId = null;

        if (crewIdParam != null) {
            finalCrewId = crewIdParam;
        } else if (crewIdHeader != null) {
            finalCrewId = crewIdHeader;
        } else {
            // Fall back to SecurityContext (assuming you store crewId in your principal or JWT)
            finalCrewId = crewService.getIdFromContext();
        }
        return ResponseEntity.ok(taskService.getTasksByCrewId(finalCrewId,page,size));
    }
    @GetMapping("/get-task-by-crew-id/{crewId}")
    public ResponseEntity<PageResult<CrewTaskDtoOutPut>> getCrewTaskById(@PathVariable(value = "crewId", required = false) long crewId,
                                                                         @RequestParam(defaultValue = "0",required = false) int page,
                                                                         @RequestParam(defaultValue = "10",required = false) int size) throws ServiceBaseException {

        return ResponseEntity.ok(taskService.getTasksByCrewId(crewId,page,size));
    }
    @GetMapping("/get-finished-task-by-crew-id/{crewId}")
    public ResponseEntity<PageResult<CrewTaskDtoOutPut>> getFinishedTasksByCrewId(@PathVariable("crewId") long crewId,
                                                                         @RequestParam(defaultValue = "0",required = false) int page,
                                                                         @RequestParam(defaultValue = "10",required = false) int size) {
        return ResponseEntity.ok(taskService.getFinishedTasksByCrewId(crewId,page,size));
    }

    @GetMapping("/get-unfinished-task-by-crew-id/{crewId}")
    public ResponseEntity<PageResult<CrewTaskDtoOutPut>> getUnfinishedTasksByCrewId(@PathVariable("crewId") long crewId,
                                                                                    @RequestParam(defaultValue = "0",required = false) int page,
                                                                                    @RequestParam(defaultValue = "10",required = false) int size) {
        return ResponseEntity.ok(taskService.getUnfinishedTasksByCrewId(crewId,page,size));
    }

    @GetMapping("/get-unfinished-task-crossed-deadline-by-crew-id/{crewId}")
    public ResponseEntity<PageResult<CrewTaskDtoOutPut>> getUnfinishedTasksCrossedDeadlineByCrewId(@PathVariable("crewId") long crewId,
                                                                                    @RequestParam(defaultValue = "0",required = false) int page,
                                                                                    @RequestParam(defaultValue = "10",required = false) int size) {
        return ResponseEntity.ok(taskService.getUnfinishedTasksCrossedDeadlineByCrewId(crewId,page,size));
    }
    @PostMapping("/finish-task")
    public ResponseEntity<String> finishTask(@RequestBody MaintenanceLogDto maintenanceLogDto) {
        String str =  maintenanceLogService.finishTask(maintenanceLogDto);
        return ResponseEntity.ok(str);
    }

}
