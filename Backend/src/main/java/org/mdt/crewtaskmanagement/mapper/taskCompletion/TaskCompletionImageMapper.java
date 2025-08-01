package org.mdt.crewtaskmanagement.mapper.taskCompletion;

import lombok.Builder;
import lombok.Data;
import org.mdt.crewtaskmanagement.dto.taskCompletion.TaskCompletionImageDto;
import org.mdt.crewtaskmanagement.model.MaintenanceLog;
import org.mdt.crewtaskmanagement.model.TaskCompletionImage;

@Data
@Builder
public class TaskCompletionImageMapper {
    public static TaskCompletionImage fromDto(TaskCompletionImageDto  dto) {
        TaskCompletionImage taskCompletionImage = new TaskCompletionImage();
        if(dto.getId() != null){
            taskCompletionImage.setId(dto.getId());
        }
        taskCompletionImage.setImageUrl(dto.getImageUrl());
        taskCompletionImage.setImageType(TaskCompletionImage.ImageType.valueOf(dto.getImageType()));
        return taskCompletionImage;
    }

    public static TaskCompletionImageDto toDto(TaskCompletionImage taskCompletionImage) {
        return new TaskCompletionImageDto(
                taskCompletionImage.getId(),
                taskCompletionImage.getImageUrl(),
                taskCompletionImage.getImageType().toString(),
                taskCompletionImage.getMaintenanceLog().getId()
        );
    }

}
