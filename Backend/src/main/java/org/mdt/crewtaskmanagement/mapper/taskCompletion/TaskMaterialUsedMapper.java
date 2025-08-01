package org.mdt.crewtaskmanagement.mapper.taskCompletion;

import org.mdt.crewtaskmanagement.dto.taskCompletion.TaskMaterialUsedDto;
import org.mdt.crewtaskmanagement.model.TaskMaterialUsed;

public class TaskMaterialUsedMapper {
    public static TaskMaterialUsed fromDto(TaskMaterialUsedDto dto) {
        return TaskMaterialUsed.builder()
                .materialName(dto.getMaterialName())
                .quantityUsed(dto.getQuantityUsed())
                .unit(dto.getUnit())
                .build();
    }

    public static TaskMaterialUsedDto toDto(TaskMaterialUsed taskMaterialUsed) {
        return TaskMaterialUsedDto.builder()
                .id(taskMaterialUsed.getId())
                .maintenanceLogId(taskMaterialUsed.getMaintenanceLog().getId())
                .materialName(taskMaterialUsed.getMaterialName())
                .quantityUsed(taskMaterialUsed.getQuantityUsed())
                .unit(taskMaterialUsed.getUnit())
                .build();
    }
}
