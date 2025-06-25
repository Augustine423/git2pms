package org.mdt.crewtaskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.ship.ShipDto;
import org.mdt.crewtaskmanagement.mapper.ShipMapper;
import org.mdt.crewtaskmanagement.model.Company;
import org.mdt.crewtaskmanagement.model.Ship;
import org.mdt.crewtaskmanagement.repository.entity.CompanyRepository;
import org.mdt.crewtaskmanagement.repository.entity.ShipRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ShipServiceImpl {
    private final ShipRepository shipRepository;
    private final CompanyRepository companyRepository;

    public ShipDto registerShip(ShipDto shipDto){
        Ship ship = ShipMapper.fromDto(shipDto);
        Company company = companyRepository.findById(shipDto.getCompanyId()).orElseThrow();
        ship.setCompany(company);
        Ship savedShip = shipRepository.save(ship);
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
    public List<ShipDto> getAllShips(){
        return shipRepository.findAll().stream().map(ShipMapper::toDto).collect(Collectors.toList());
    }
    public void deleteShipById(long id){
        shipRepository.deleteById(id);
    }

}
