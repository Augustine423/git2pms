package org.mdt.crewtaskmanagement.mapper;

import org.mdt.crewtaskmanagement.dto.reportrequest.ReportRequestDto;
import org.mdt.crewtaskmanagement.model.ReportRequest;
import org.mdt.crewtaskmanagement.model.Crew;
import org.mdt.crewtaskmanagement.model.TaskAssignment;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ReportRequestMapper {

    public static ReportRequest fromDto(ReportRequestDto dto, Crew crew, TaskAssignment taskSchedule) {
        if (dto == null) {
            return null;
        }

        ReportRequest reportRequest = new ReportRequest();
        reportRequest.setTitle(dto.getTitle());
        reportRequest.setContent(dto.getContent());
        reportRequest.setReportType(dto.getReportType());
        reportRequest.setRequestDate(parseDate(dto.getRequestDate()));
        reportRequest.setCrew(crew);
        reportRequest.setTaskAssignment(taskSchedule);

        // Only set ID if present in DTO (not 0)
        if (dto.getId() != 0L) {
            reportRequest.setId(dto.getId());
        }

        return reportRequest;
    }

    public static ReportRequestDto toDto(ReportRequest entity) {
        if (entity == null) {
            return null;
        }

        return ReportRequestDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .reportType(entity.getReportType())
                .crewId(entity.getCrew().getId())
                .t_a_id(entity.getTaskAssignment().getId())
                .requestDate(formatDate(entity.getRequestDate()))
                .build();
    }

    // Optional: Include related entity IDs in the DTO
    public static ReportRequestDto toDtoWithRelations(ReportRequest entity) {
        if (entity == null) {
            return null;
        }

        ReportRequestDto dto = toDto(entity);
        // Add related entity IDs if needed
        return dto;
    }

    private static LocalDate parseDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateString, e);
        }
    }

    private static String formatDate(LocalDate date) {
        return date != null ? date.toString() : null;
    }
}
