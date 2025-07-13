package org.mdt.crewtaskmanagement.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.ship.ShipDto;
import org.mdt.crewtaskmanagement.mapper.ShipMapper;
import org.mdt.crewtaskmanagement.model.Company;
import org.mdt.crewtaskmanagement.model.Ship;
import org.mdt.crewtaskmanagement.output.PageResult;
import org.mdt.crewtaskmanagement.repository.entity.CompanyRepository;
import org.mdt.crewtaskmanagement.repository.entity.ShipRepository;
import org.mdt.crewtaskmanagement.service.IShipService;
import org.mdt.crewtaskmanagement.utils.GetEntitesWithPageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ShipServiceImpl implements IShipService {
    private final ShipRepository shipRepository;
    private final CompanyRepository companyRepository;

    public ShipDto registerShip(ShipDto shipDto) {
        // Convert DTO to entity (new Ship)

        Ship ship = ShipMapper.fromDto(shipDto);
        System.out.println(ship.getShipOffice()+ ship.getName());

        // Always use managed entities for relationships
        Company company = companyRepository.findById(shipDto.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        ship.setCompany(company);

        // Save the new Ship
        Ship savedShip = shipRepository.save(ship);

        // Convert and return DTO
        return ShipMapper.toDto(savedShip);
    }



    public ShipDto updateShip(ShipDto shipDto){
        Ship ship = ShipMapper.fromDto(shipDto);
        Company company = companyRepository.findById(shipDto.getCompanyId()).orElseThrow();
        ship.setCompany(company);
        Ship savedShip = shipRepository.save(ship);
        savedShip.setId(ship.getId());
        return ShipMapper.toDto(savedShip);
    }
    public ShipDto getShipById(long id){
        return ShipMapper.toDto(shipRepository.findById(id).orElseThrow());
    }

    @Override
    public Ship getById(long id) {
     return  shipRepository.findById(id).orElseThrow();
    }

    @Override
    public PageResult<ShipDto> getAllShips(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Ship> ships = shipRepository.findAll(pageable);
        return  GetEntitesWithPageable.getAllWithPageable(pageable,ships,ShipMapper::toDto);
    }
    public void deleteShipById(long id){
        shipRepository.deleteById(id);
    }

    @Override
    public PageResult<ShipDto> getShipsByCompanyId(long companyId, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        var ship =  shipRepository.getShipByCompanyId(companyId,pageable);
        return GetEntitesWithPageable.getAllWithPageable(pageable,ship,ShipMapper::toDto);
    }

}
