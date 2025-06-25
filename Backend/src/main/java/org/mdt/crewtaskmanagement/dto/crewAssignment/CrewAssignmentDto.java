package org.mdt.crewtaskmanagement.dto.crewAssignment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrewAssignmentDto {
    private long id;
    private long crewId;
    private long shipId;
    private String position;
    private String startDate;
    private String endDate;

}
