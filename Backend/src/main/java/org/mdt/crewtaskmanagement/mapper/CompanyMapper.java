package org.mdt.crewtaskmanagement.mapper;

import org.mdt.crewtaskmanagement.dto.company.CompanyDto;
import org.mdt.crewtaskmanagement.model.Company;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CompanyMapper {

    public static Company fromDto(CompanyDto dto) {
        if (dto == null) {
            return null;
        }

        Company company = new Company();
        company.setName(dto.getName());
        company.setRegisteredBy(dto.getRegisteredBy());
        company.setCompanyAddress(dto.getCompanyAddress());
        company.setCompanyEmail(dto.getCompanyEmail());
        company.setYearEstablished(parseDate(dto.getYearEstablished()));
        company.setCompanyPhone(dto.getCompanyPhone());
        company.setCompanyFax(dto.getCompanyFax());

        // Only set ID if present in DTO (not 0)
        if (dto.getId() != 0L) {
            company.setId(dto.getId());
        }

        return company;
    }

    public static CompanyDto toDto(Company entity) {
        if (entity == null) {
            return null;
        }

        return CompanyDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .registeredBy(entity.getRegisteredBy())
                .companyAddress(entity.getCompanyAddress())
                .companyEmail(entity.getCompanyEmail())
                .yearEstablished(formatDate(entity.getYearEstablished()))
                .companyPhone(entity.getCompanyPhone())
                .companyFax(entity.getCompanyFax())
                .build();
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
