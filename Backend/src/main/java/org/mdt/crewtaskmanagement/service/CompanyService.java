package org.mdt.crewtaskmanagement.service;

import org.mdt.crewtaskmanagement.dto.company.CompanyDto;

import java.util.List;

public interface CompanyService {
    CompanyDto registerCompany(CompanyDto dto);
    CompanyDto updateCompany(CompanyDto dto);
    CompanyDto getCompanyById(long id);
    List<CompanyDto> getAllCompanies();
    void deleteCompanyById(long id);

}
