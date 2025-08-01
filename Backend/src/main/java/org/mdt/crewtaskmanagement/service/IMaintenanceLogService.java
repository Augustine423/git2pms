package org.mdt.crewtaskmanagement.service;

import org.mdt.crewtaskmanagement.dto.maintenancelog.MaintenanceLogDto;
import org.mdt.crewtaskmanagement.model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IMaintenanceLogService {



 String finishTask(MaintenanceLogDto maintenanceLogDto);
  LocalDateTime calculateNextDate(LocalDateTime from, int interval, Task.IntervalUnit unit);


}
