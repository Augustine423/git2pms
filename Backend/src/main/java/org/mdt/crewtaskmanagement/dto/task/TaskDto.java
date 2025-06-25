package org.mdt.crewtaskmanagement.dto.task;

import lombok.Builder;
import lombok.Data;

@Data
public class TaskDto {
    private long id;

    private String title;

    private String description;

    private String position;

    private String componentName;// this is for location

    private String taskType;

    private String kind;

    private boolean critical;

}
