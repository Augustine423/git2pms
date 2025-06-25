package org.mdt.crewtaskmanagement.service;

import org.mdt.crewtaskmanagement.dto.maintenancelog.MaintenanceLogDto;
import org.mdt.crewtaskmanagement.model.MaintenanceLog;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface MaintenanceLogService {


 void createMaintenanceLog(MaintenanceLogDto dto);
 String finishTask(Long assignmentId,String remark);

}
