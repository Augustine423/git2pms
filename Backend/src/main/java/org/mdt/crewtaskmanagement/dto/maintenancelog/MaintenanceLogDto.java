package org.mdt.crewtaskmanagement.dto.maintenancelog;

import lombok.Builder;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Data
public class MaintenanceLogDto {
    private Long id;
    private long taskId;
    private long crewId;
    private String crewName;
    private String lastWork;
    private String nextDue;
    private String remarks;
}
