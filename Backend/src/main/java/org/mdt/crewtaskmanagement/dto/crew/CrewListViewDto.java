package org.mdt.crewtaskmanagement.dto.crew;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CrewListViewDto {
    private long id;
    private String firstName;
    private String lastName;
    private String assignedPart;
    private String nationality;
    private  String status;
}
