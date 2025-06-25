package org.mdt.crewtaskmanagement.mapper;

import org.mdt.crewtaskmanagement.dto.ship.ShipDto;
import org.mdt.crewtaskmanagement.model.Ship;
import org.mdt.crewtaskmanagement.model.Company;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ShipMapper {

    public static Ship fromDto(ShipDto dto) {
        if (dto == null) {
            return null;
        }

        Ship ship = new Ship();
        ship.setName(dto.getName());
        ship.setImoNumber(dto.getImoNumber());
        ship.setFlag(dto.getFlag());
        ship.setType(dto.getType());
        ship.setYearBuilt(parseDate(dto.getYearBuilt()));
        ship.setMmsi(dto.getMmsi());
        ship.setShipOffice(dto.getShipOffice());


        // Only set ID if present in DTO (not 0)
        if (dto.getId() != 0L) {
            ship.setId(dto.getId());
        }

        return ship;
    }

    public static ShipDto toDto(Ship entity) {
        if (entity == null) {
            return null;
        }

        return ShipDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .companyId(entity.getCompany().getId())
                .imoNumber(entity.getImoNumber())
                .flag(entity.getFlag())
                .type(entity.getType())
                .yearBuilt(formatDate(entity.getYearBuilt()))
                .mmsi(entity.getMmsi())
                .shipOffice(entity.getShipOffice())
                .build();
    }

    // Optional: Include company information in the DTO
    public static ShipDto toDtoWithCompany(Ship entity) {
        if (entity == null) {
            return null;
        }

        ShipDto dto = toDto(entity);
        // Add company ID or other company info if needed
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
