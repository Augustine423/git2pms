package org.mdt.crewtaskmanagement.dto.task;

import lombok.Builder;
import lombok.Data;
import org.mdt.crewtaskmanagement.model.Task;

@Data
public class TaskDto {
    private long id;

    private String title;

    private String description;

    private String position;

    private boolean critical;

    private String componentName;

    private int intervalValue;

    private String intervalUnit;

    private String kind;// this is for location





}
