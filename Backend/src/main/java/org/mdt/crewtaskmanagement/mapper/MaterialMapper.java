package org.mdt.crewtaskmanagement.mapper;

import org.mdt.crewtaskmanagement.dto.material.MaterialDto;
import org.mdt.crewtaskmanagement.model.Material;
import org.mdt.crewtaskmanagement.model.Ship;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class MaterialMapper {

    public static Material fromDto(MaterialDto dto) {
        if (dto == null) {
            return null;
        }

        Material material = new Material();
        material.setSerialNo(dto.getSerialNo());
        material.setName(dto.getName());
        material.setCondition(dto.getCondition());
        material.setType(dto.getType());
        material.setDescription(dto.getDescription());
        material.setUseStatus(dto.isUseStatus());
        material.setManufacturer(dto.getManufacturer());
        material.setPrice(dto.getPrice());
        material.setCondition(dto.getCondition());
        material.setSupplierInfo(dto.getSupplierInfo());
        material.setReceivedDate(parseDate(dto.getReceivedDate()));
        material.setLifeTime(dto.getLifeTime());


        // Only set ID if present in DTO (not 0)
        if (dto.getId() != 0L) {
            material.setId(dto.getId());
        }

        return material;
    }

    public static MaterialDto toDto(Material entity) {
        if (entity == null) {
            return null;
        }

        return MaterialDto.builder()
                .id(entity.getId())
                .serialNo(entity.getSerialNo())
                .name(entity.getName())
                .type(entity.getType())
                .condition(entity.getCondition())
                .description(entity.getDescription())
                .useStatus(entity.isUseStatus())
                .manufacturer(entity.getManufacturer())
                .price(entity.getPrice())
                .condition(entity.getCondition())
                .supplierInfo(entity.getSupplierInfo())
                .receivedDate(formatDate(entity.getReceivedDate()))
                .lifeTime(entity.getLifeTime())
                .build();
    }

    // Optional: Include ship information in the DTO
    public static MaterialDto toDtoWithShip(Material entity) {
        if (entity == null) {
            return null;
        }

        MaterialDto dto = toDto(entity);
        // Add ship ID or other ship info if needed
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
