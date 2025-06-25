package org.mdt.crewtaskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.company.CompanyDto;
import org.mdt.crewtaskmanagement.mapper.CompanyMapper;
import org.mdt.crewtaskmanagement.model.Company;
import org.mdt.crewtaskmanagement.model.Ship;
import org.mdt.crewtaskmanagement.repository.entity.CompanyRepository;
import org.mdt.crewtaskmanagement.repository.entity.ShipRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl {
    private final CompanyRepository companyRepository;
    private final ShipRepository shipRepository;
    @Transactional
    public CompanyDto registerCompany(CompanyDto dto){
        Company company = CompanyMapper.fromDto(dto);
        companyRepository.save(company);
        return CompanyMapper.toDto(company);
    }
    public CompanyDto updateCompany(CompanyDto dto){
        Company company = CompanyMapper.fromDto(dto);
        company.setId(dto.getId());
        companyRepository.save(company);
        return CompanyMapper.toDto(company);
    }
    public CompanyDto getCompanyById(long id){
       return CompanyMapper.toDto(companyRepository.findById(id).orElseThrow());
    }

    public List<CompanyDto> getAllCompanies(){
        return (companyRepository.findAll().stream().map(CompanyMapper::toDto).collect(Collectors.toList()));
    }

    public void deleteCompanyById(long id){
        companyRepository.deleteById(id);
    }

    public void addShipsToCompany(long companyId, long shipId){
        Company company = companyRepository.findById(companyId).orElseThrow();
        Ship ship = shipRepository.findById(shipId).orElseThrow();
        company.addShip(ship);
    }

}
