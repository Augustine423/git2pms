package org.mdt.crewtaskmanagement.dto.company;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class CompanyDto {
    private long id;
    private String name;
    private String registeredBy;
    private String companyAddress;
    private String companyEmail;
    private String yearEstablished;
    private String companyPhone;
    private String companyFax;
}
