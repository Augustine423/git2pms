package org.mdt.crewtaskmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.model.ReportRequest;
import org.mdt.crewtaskmanagement.model.TaskAssignment;
import org.mdt.crewtaskmanagement.service.MaintenanceLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaskScheduleController {
    private final MaintenanceLogService maintenanceLogService;

    @PostMapping("/task/{assignmentId}/finish")
    public ResponseEntity<String> finishTask(@PathVariable Long assignmentId, @RequestBody String remark) {
      String res =  maintenanceLogService.finishTask(assignmentId, remark);
      return ResponseEntity.ok(res);
    }
}
