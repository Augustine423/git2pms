package org.mdt.crewtaskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.crewAssignment.CrewAssignmentDto;
import org.mdt.crewtaskmanagement.model.CrewAssignment;
import org.mdt.crewtaskmanagement.repository.entity.CrewAssignmentRepository;
import org.mdt.crewtaskmanagement.repository.entity.CrewRepository;
import org.mdt.crewtaskmanagement.repository.entity.ShipRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class CrewAssignmentServiceImpl {
    private final CrewAssignmentRepository crewAssignmentRepository;
    private final CrewRepository crewRepository;
    private final ShipRepository shipRepository;

    public CrewAssignmentDto addCrewAssignment(CrewAssignmentDto crewAssignmentDto) {
       var ca = fromDto(crewAssignmentDto);
         var  c = crewAssignmentRepository.save(ca);
         return toDto(c);
    }
    //_______________________________________________________________________________________//
    public CrewAssignment fromDto(CrewAssignmentDto dto) {
        CrewAssignment crewAssignment = new CrewAssignment();
        if(dto.getId() != 0L){
            crewAssignment.setId(dto.getId());
        }
        crewAssignment.setCrew(crewRepository.findById(dto.getCrewId()).orElseThrow());
        crewAssignment.setShip(shipRepository.findById(dto.getShipId()).orElseThrow());
        crewAssignment.setPosition(dto.getPosition());
        crewAssignment.setStartDate(LocalDate.parse(dto.getStartDate()));
        crewAssignment.setEndDate(LocalDate.parse(dto.getEndDate()));
        return crewAssignment;
    }
    public CrewAssignmentDto toDto(CrewAssignment crewAssignment) {
        CrewAssignmentDto crewAssignmentDto = new CrewAssignmentDto();
        crewAssignmentDto.setId(crewAssignment.getId());
        crewAssignmentDto.setPosition(crewAssignment.getPosition());
        crewAssignmentDto.setStartDate(crewAssignment.getStartDate().toString());
        crewAssignmentDto.setEndDate(crewAssignment.getEndDate().toString());
        crewAssignmentDto.setShipId(crewAssignment.getShip().getId());
        crewAssignmentDto.setCrewId(crewAssignment.getCrew().getId());
        return crewAssignmentDto;
    }



}
