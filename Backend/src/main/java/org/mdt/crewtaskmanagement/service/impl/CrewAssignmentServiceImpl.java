package org.mdt.crewtaskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.crewAssignment.CrewAssignmentDto;
import org.mdt.crewtaskmanagement.mapper.crew.CrewAssignmentMapper;
import org.mdt.crewtaskmanagement.model.CrewAssignment;
import org.mdt.crewtaskmanagement.repository.entity.CrewAssignmentRepository;
import org.mdt.crewtaskmanagement.repository.entity.CrewRepository;
import org.mdt.crewtaskmanagement.repository.entity.ShipRepository;
import org.mdt.crewtaskmanagement.service.ICrewAssignmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class CrewAssignmentServiceImpl implements ICrewAssignmentService {

    private final CrewAssignmentRepository crewAssignmentRepository;
    private final CrewRepository crewRepository;
    private final ShipRepository shipRepository;
    private final CrewAssignmentMapper crewAssignmentMapper;

    @Override
    public CrewAssignmentDto createCrewAssignment(CrewAssignmentDto crewAssignmentDto) {
        CrewAssignment crewAssignment = crewAssignmentMapper.fromDto(crewAssignmentDto);
        return crewAssignmentMapper.toDto(crewAssignmentRepository.save(crewAssignment));
    }
}
