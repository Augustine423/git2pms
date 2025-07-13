package org.mdt.crewtaskmanagement.service;

import org.mdt.crewtaskmanagement.dto.company.CompanyDto;
import org.mdt.crewtaskmanagement.model.Company;
import org.mdt.crewtaskmanagement.output.PageResult;

import java.util.List;

public interface ICompanyService {
    CompanyDto registerCompany(CompanyDto dto);
    CompanyDto updateCompany(CompanyDto dto);
    CompanyDto getCompanyById(long id);
    Company getById(long id);
    PageResult<CompanyDto> getAllCompanies(int page, int size);
    void deleteCompanyById(long id);

}
