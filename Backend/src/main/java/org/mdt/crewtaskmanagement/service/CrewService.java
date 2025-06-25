package org.mdt.crewtaskmanagement.service;

import org.mdt.crewtaskmanagement.dto.crew.CrewDto;
import org.mdt.crewtaskmanagement.exception.ServiceBaseException;
import org.mdt.crewtaskmanagement.model.Crew;
import org.mdt.crewtaskmanagement.param.CrewParam;

import java.rmi.server.ServerCloneException;
import java.util.List;

public interface CrewService {
    public CrewDto registerCrew(CrewDto c) throws ServiceBaseException;
    public CrewDto updateCrew(CrewDto c);
    public CrewDto getCrewById(long id);
    public List<CrewDto> getAllCrews();
    public void deleteCrew(long id);
    List<CrewDto> getCrewsForAssignments();
    List<CrewDto> getAllCrewsByShipId(long shipId);

    List<CrewDto> search(CrewParam param);
    int getAllCrewsCount();
    int getAllActiveCrewCount();

}
