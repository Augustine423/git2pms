package org.mdt.crewtaskmanagement.dto.material;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class MaterialDto {
    private long id;
    private String serialNo;
    private String name;
    private String type;
    private String description;
    private boolean useStatus;
    private String manufacturer;
    private long price;
    private String condition;
    private String supplierInfo;
    private String receivedDate;
    private String lifeTime;
}
