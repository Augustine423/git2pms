package org.mdt.crewtaskmanagement.dto.taskCompletion;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.mdt.crewtaskmanagement.model.MaintenanceLog;

@Data
@Builder
@AllArgsConstructor
public class TaskMaterialUsedDto {
    private Long id;
    private long maintenanceLogId;
    private String materialName;
    private int quantityUsed;
    private String unit;
}
