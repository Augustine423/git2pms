package org.mdt.crewtaskmanagement.mapper;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.materialReportRequest.MaterialReportRequestDto;
import org.mdt.crewtaskmanagement.dto.reportrequest.ReportRequestDto;
import org.mdt.crewtaskmanagement.dto.reportrequest.MaterialRequestDto;
import org.mdt.crewtaskmanagement.model.Material;
import org.mdt.crewtaskmanagement.model.ReportRequest;
import org.mdt.crewtaskmanagement.model.ReportRequest.ReportStatus;
import org.mdt.crewtaskmanagement.model.MaterialReportRequest;
import org.mdt.crewtaskmanagement.service.ICrewService;

import org.mdt.crewtaskmanagement.service.ITaskService;
import org.mdt.crewtaskmanagement.service.impl.MaterialServiceImpl;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReportRequestMapper {

    private final ICrewService crewService;
    private final ITaskService taskService;
    private final MaterialServiceImpl materialServiceImpl;

    public ReportRequest fromDto(ReportRequestDto dto) {
        if (dto == null) return null;

        ReportRequest entity = new ReportRequest();

        if (dto.getId() != 0L) {
            entity.setId(dto.getId());
        }

        entity.setTitle(dto.getTitle());
        entity.setCrew(crewService.getById(dto.getCrewId()));
        entity.setContent(dto.getContent());
        entity.setTaskAssignment(taskService.getTaskAssignmentById(dto.getTaskAssignmentId()));
        entity.setReportType(dto.getReportType());
        entity.setRequestDate(dto.getRequestDate() != null ? LocalDate.parse(dto.getRequestDate()) : null);

        if (dto.getStatus() != null) {
            entity.setStatus(ReportStatus.valueOf(dto.getStatus()));
        } else {
            entity.setStatus(ReportStatus.PENDING); // default
        }

        // Map MaterialRequestDto to MaterialReportRequest if needed
        // entity.setMaterialReportRequests(...)

        return entity;
    }

    public ReportRequestDto toDto(ReportRequest entity) {
        if (entity == null) return null;

        return ReportRequestDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .crewId(entity.getCrew() != null ? entity.getCrew().getId() : 0L)
                .taskAssignmentId(entity.getTaskAssignment() != null ? entity.getTaskAssignment().getId() : 0L)
                .reportType(entity.getReportType())
                .content(entity.getContent())
                .requestDate(entity.getRequestDate() != null ? entity.getRequestDate().toString() : null)
                .status(entity.getStatus() != null ? entity.getStatus().name() : null)
                .requestedMaterials(entity.getMaterialReportRequests() != null ?
                        entity.getMaterialReportRequests().stream()
                                .collect(Collectors.groupingBy(
                                        mrr -> mrr.getMaterial().getName(), // group by material name
                                        Collectors.counting()               // count occurrences
                                ))
                                .entrySet().stream()
                                .map(entry -> MaterialRequestDto.builder()
                                        .materialName(entry.getKey())
                                        .quantity(entry.getValue().intValue())
                                        .build())
                                .collect(Collectors.toList())
                        : null)
                .build();
    }


    private List<MaterialReportRequest> getMaterialReportRequestFromDto(ReportRequestDto reportRequestDto) {
        if(!reportRequestDto.getRequestedMaterials().isEmpty()) {
            List<MaterialReportRequest> materialReportRequests = new ArrayList<>();


            for (var requestedMaterial : reportRequestDto.getRequestedMaterials()) {
                List<Material> materials = materialServiceImpl.findByMaterialName(requestedMaterial.getMaterialName(), requestedMaterial.getQuantity());

                if (!materials.isEmpty()) {


                    for (Material material : materials) {
                        System.out.println(material.getId() + " " + material.getName());
                        MaterialReportRequest mrr = new MaterialReportRequest();
                        mrr.setMaterial(material);
                        System.out.println(material.getName());
                        mrr.setReportRequest(fromDto(reportRequestDto));
                        try {
                            materialReportRequests.add(mrr);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("not working");
                }
            }
            return materialReportRequests;
        }
        throw  new RuntimeException("not working or the requested material is empty");

    }
}
