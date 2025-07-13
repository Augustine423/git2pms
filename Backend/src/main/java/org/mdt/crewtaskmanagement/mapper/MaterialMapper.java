package org.mdt.crewtaskmanagement.mapper;

import org.mdt.crewtaskmanagement.dto.material.MaterialDto;
import org.mdt.crewtaskmanagement.model.Material;
import org.mdt.crewtaskmanagement.model.Ship;

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

        if (dto.getStatus() != null) {
            material.setStatus(Material.MaterialStatus.valueOf(dto.getStatus()));
        }

        material.setManufacturer(dto.getManufacturer());
        material.setPrice(dto.getPrice());
        material.setSupplierInfo(dto.getSupplierInfo());
        material.setReceivedDate(parseDate(dto.getReceivedDate()));
        material.setLifeTimeHours(dto.getLifeTimeHours());
        material.setExpectedExpiryDate(parseDate(dto.getExpectedExpiryDate()));
        material.setLocationCode(dto.getLocationCode());



        // Only set ID if present in DTO
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
                .status(entity.getStatus() != null ? entity.getStatus().toString() : null)
                .manufacturer(entity.getManufacturer())
                .price(entity.getPrice())
                .supplierInfo(entity.getSupplierInfo())
                .receivedDate(formatDate(entity.getReceivedDate()))
                .lifeTimeHours(entity.getLifeTimeHours())
                .expectedExpiryDate(formatDate(entity.getExpectedExpiryDate()))
                .locationCode(entity.getLocationCode())
                .shipId(entity.getShip() != null ? entity.getShip().getId() : null)
                .build();
    }

    // Optional method to include more ship info
    public static MaterialDto toDtoWithShip(Material entity) {
        return toDto(entity); // Extend this later if you need more ship details
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
