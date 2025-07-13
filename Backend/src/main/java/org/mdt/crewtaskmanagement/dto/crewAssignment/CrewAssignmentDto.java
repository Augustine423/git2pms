package org.mdt.crewtaskmanagement.dto.crewAssignment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrewAssignmentDto {
    private long id;
    private long crewId;
    private long shipId;
    private long assignedBy;
    private long reportTo;
    private String startDate;
    private String endDate;
    private String rank;
    private String section;
    private boolean active;
    private String remarks;
    private String position;
}
