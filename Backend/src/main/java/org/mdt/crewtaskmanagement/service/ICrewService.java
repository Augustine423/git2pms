package org.mdt.crewtaskmanagement.service;

import org.mdt.crewtaskmanagement.dto.crew.CrewDto;
import org.mdt.crewtaskmanagement.dto.crewAssignment.CrewAssignmentDto;
import org.mdt.crewtaskmanagement.exception.ServiceBaseException;
import org.mdt.crewtaskmanagement.model.Crew;
import org.mdt.crewtaskmanagement.output.PageResult;
import org.mdt.crewtaskmanagement.param.CrewParam;

import java.util.List;

public interface ICrewService {
    CrewDto registerCrew(CrewDto c) throws ServiceBaseException;
    CrewDto updateCrew(CrewDto c);
    CrewDto getCrewById(long id);
    Crew getById(long id);
    PageResult<CrewDto> getAllCrews(int page,int size);
    void deleteCrew(long id);
    PageResult<CrewDto> getCrewsForAssignments(int page, int size);
    PageResult<CrewDto> getAllCrewsByShipId(long shipId,int page, int size);
    PageResult<CrewDto> getCrewsWithPosition(String rank,int page, int size);
    PageResult<CrewDto> getAllCrewsWithPositionByShipId(long shipId,String rank,int page, int size);
    List<CrewDto> search(CrewParam param);
    int getAllCrewsCount();
    int getAllActiveCrewCount();
    CrewAssignmentDto addCrewAssignment(CrewAssignmentDto crewAssignmentDto);
}
