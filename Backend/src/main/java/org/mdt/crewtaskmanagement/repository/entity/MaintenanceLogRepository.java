package org.mdt.crewtaskmanagement.repository.entity;

import org.mdt.crewtaskmanagement.model.Maintenance;
import org.mdt.crewtaskmanagement.model.MaintenanceLog;
import org.mdt.crewtaskmanagement.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceLogRepository extends BaseRepository<MaintenanceLog, Long> {
}
