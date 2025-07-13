package org.mdt.crewtaskmanagement.service;

import org.mdt.crewtaskmanagement.dto.crewAssignment.CrewAssignmentDto;
import org.mdt.crewtaskmanagement.model.Crew;

public interface ICrewAssignmentService {
    public CrewAssignmentDto createCrewAssignment(CrewAssignmentDto crewAssignmentDto);
}
