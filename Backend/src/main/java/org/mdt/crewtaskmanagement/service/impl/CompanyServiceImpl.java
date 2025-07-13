package org.mdt.crewtaskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.company.CompanyDto;
import org.mdt.crewtaskmanagement.mapper.CompanyMapper;
import org.mdt.crewtaskmanagement.model.Company;
import org.mdt.crewtaskmanagement.model.Ship;
import org.mdt.crewtaskmanagement.output.PageResult;
import org.mdt.crewtaskmanagement.repository.entity.CompanyRepository;
import org.mdt.crewtaskmanagement.repository.entity.ShipRepository;
import org.mdt.crewtaskmanagement.service.ICompanyService;
import org.mdt.crewtaskmanagement.utils.GetEntitesWithPageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyRepository companyRepository;
    private final ShipRepository shipRepository;

    @Transactional
    public CompanyDto registerCompany(CompanyDto dto){
        Company company = CompanyMapper.fromDto(dto);
        companyRepository.save(company);
        return CompanyMapper.toDto(company);
    }

    @Transactional
    public CompanyDto updateCompany(CompanyDto dto){
        Company company = CompanyMapper.fromDto(dto);
        return CompanyMapper.toDto(companyRepository.save(company));
    }

    public CompanyDto getCompanyById(long id){
       return CompanyMapper.toDto(companyRepository.findById(id).orElseThrow());
    }

    @Override
    public Company getById(long id) {
        return companyRepository.findById(id).orElseThrow();
    }

    public PageResult<CompanyDto> getAllCompanies(int page, int size){
        var pageable = PageRequest.of(page, size);
        Page<Company> companies = companyRepository.findAll(pageable);
        return GetEntitesWithPageable.getAllWithPageable(pageable,companies,CompanyMapper::toDto);
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
