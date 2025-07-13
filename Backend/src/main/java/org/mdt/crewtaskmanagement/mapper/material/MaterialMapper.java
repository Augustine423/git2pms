package org.mdt.crewtaskmanagement.mapper.material;

import org.mdt.crewtaskmanagement.dto.material.MaterialDto;
import org.mdt.crewtaskmanagement.model.Material;
import org.mdt.crewtaskmanagement.model.Material.MaterialStatus;
import org.mdt.crewtaskmanagement.model.Ship;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class MaterialMapper {

    public static Material fromDto(MaterialDto dto, Ship ship) {
        if (dto == null) {
            return null;
        }

        Material material = new Material();

        if (dto.getId() != 0L) {
            material.setId(dto.getId());
        }

        material.setSerialNo(dto.getSerialNo());
        material.setName(dto.getName());
        material.setDescription(dto.getDescription());
        material.setType(dto.getType());
        material.setManufacturer(dto.getManufacturer());
        material.setPrice(dto.getPrice());
        material.setCondition(dto.getCondition());
        material.setSupplierInfo(dto.getSupplierInfo());
        material.setReceivedDate(parseDate(dto.getReceivedDate()));
        material.setLifeTimeHours(dto.getLifeTimeHours());
        material.setExpectedExpiryDate(parseDate(dto.getExpectedExpiryDate()));
        material.setLocationCode(dto.getLocationCode());

        if (dto.getStatus() != null) {
            try {
                material.setStatus(MaterialStatus.valueOf(dto.getStatus()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid material status: " + dto.getStatus(), e);
            }
        }

        material.setShip(ship);
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
                .description(entity.getDescription())
                .status(entity.getStatus() != null ? entity.getStatus().name() : null)
                .manufacturer(entity.getManufacturer())
                .price(entity.getPrice())
                .condition(entity.getCondition())
                .supplierInfo(entity.getSupplierInfo())
                .receivedDate(formatDate(entity.getReceivedDate()))
                .lifeTimeHours(entity.getLifeTimeHours())
                .expectedExpiryDate(formatDate(entity.getExpectedExpiryDate()))
                .locationCode(entity.getLocationCode())
                .shipId(entity.getShip() != null ? entity.getShip().getId() : null)
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
