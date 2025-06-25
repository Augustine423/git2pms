package org.mdt.crewtaskmanagement.service;

import org.mdt.crewtaskmanagement.dto.ship.ShipDto;

import java.util.List;

public interface ShipService {
    ShipDto registerShip(ShipDto shipDto);
    ShipDto updateShip(ShipDto shipDto);
    ShipDto getShipById(long id);
    List<ShipDto> getAllShips();
    void deleteShipById(long id);
}
