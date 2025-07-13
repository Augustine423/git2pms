package org.mdt.crewtaskmanagement.mapper.crew;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.crewAssignment.CrewAssignmentDto;
import org.mdt.crewtaskmanagement.model.CrewAssignment;
import org.mdt.crewtaskmanagement.model.type.Section;
import org.mdt.crewtaskmanagement.repository.entity.CrewRepository;
import org.mdt.crewtaskmanagement.service.ICrewService;
import org.mdt.crewtaskmanagement.service.IShipService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class CrewAssignmentMapper {

   private final CrewRepository crewService;
    private final IShipService shipService;

    public CrewAssignment fromDto(CrewAssignmentDto dto) {
        CrewAssignment crewAssignment = new CrewAssignment();

        if (dto.getId() != 0L) {
            crewAssignment.setId(dto.getId());
        }

        crewAssignment.setCrew(crewService.findById(dto.getCrewId()).orElseThrow());
        crewAssignment.setShip(shipService.getById(dto.getShipId()));

        if (dto.getAssignedBy() != 0L) {
            crewAssignment.setAssignedBy(crewService.findById(dto.getAssignedBy()).orElseThrow());
        }

        if (dto.getReportTo() != 0L) {
            crewAssignment.setReportTo(crewService.findById(dto.getReportTo()).orElseThrow());
        }

        crewAssignment.setStartDate(dto.getStartDate() != null ? LocalDate.parse(dto.getStartDate()) : null);
        crewAssignment.setEndDate(dto.getEndDate() != null ? LocalDate.parse(dto.getEndDate()) : null);
        crewAssignment.setPosition(dto.getPosition());
        crewAssignment.setRemarks(dto.getRemarks());
        crewAssignment.setActive(dto.isActive());
        crewAssignment.setSection(dto.getSection() != null ? Section.valueOf(dto.getSection()) : null);

        return crewAssignment;
    }

    public CrewAssignmentDto toDto(CrewAssignment crewAssignment) {
        CrewAssignmentDto dto = new CrewAssignmentDto();

        dto.setId(crewAssignment.getId());
        dto.setCrewId(crewAssignment.getCrew().getId());
        dto.setShipId(crewAssignment.getShip().getId());
        dto.setAssignedBy(crewAssignment.getAssignedBy() != null ? crewAssignment.getAssignedBy().getId() : 0L);
        dto.setReportTo(crewAssignment.getReportTo() != null ? crewAssignment.getReportTo().getId() : 0L);
        dto.setStartDate(crewAssignment.getStartDate() != null ? crewAssignment.getStartDate().toString() : null);
        dto.setEndDate(crewAssignment.getEndDate() != null ? crewAssignment.getEndDate().toString() : null);
        dto.setPosition(crewAssignment.getPosition());
        dto.setRemarks(crewAssignment.getRemarks());
        dto.setActive(crewAssignment.isActive());
        dto.setSection(crewAssignment.getSection() != null ? crewAssignment.getSection().name() : null);

        return dto;
    }
}
