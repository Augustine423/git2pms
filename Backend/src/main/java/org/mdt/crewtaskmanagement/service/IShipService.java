package org.mdt.crewtaskmanagement.service;

import org.mdt.crewtaskmanagement.dto.ship.ShipDto;
import org.mdt.crewtaskmanagement.model.Ship;
import org.mdt.crewtaskmanagement.output.PageResult;

import java.util.List;

public interface IShipService {
    ShipDto registerShip(ShipDto shipDto);
    ShipDto updateShip(ShipDto shipDto);
    ShipDto getShipById(long id);
    Ship getById(long id);
    PageResult<ShipDto> getAllShips(int page, int size);
    void deleteShipById(long id);
    PageResult<ShipDto> getShipsByCompanyId(long companyId,int page, int size);
}
