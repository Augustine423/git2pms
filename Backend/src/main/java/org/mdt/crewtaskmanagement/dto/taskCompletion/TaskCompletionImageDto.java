package org.mdt.crewtaskmanagement.dto.taskCompletion;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskCompletionImageDto {
    private Long id;
    private String imageUrl;
    private String imageType;
    private long maintenanceLogId;

    public TaskCompletionImageDto(Long id, String imageUrl, String imageType, long maintenanceLogId) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.imageType = imageType;
        this.maintenanceLogId = maintenanceLogId;
    }


}
