package org.mdt.crewtaskmanagement.dto.maintenancelog;

import lombok.Builder;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.mdt.crewtaskmanagement.dto.task.TaskDto;
import org.mdt.crewtaskmanagement.dto.taskCompletion.TaskCompletionImageDto;
import org.mdt.crewtaskmanagement.dto.taskCompletion.TaskMaterialUsedDto;

import java.time.LocalDate;
import java.util.List;

@Data
public class MaintenanceLogDto {
    private Long id;
    private long taskAssignmentId;
    private String verifyBy;
    private String lastWork;
    private String nextDue;
    private String remarks;
    private List<TaskCompletionImageDto> taskCompletionImages;
    private List<TaskMaterialUsedDto> taskMaterialUsed;
}
