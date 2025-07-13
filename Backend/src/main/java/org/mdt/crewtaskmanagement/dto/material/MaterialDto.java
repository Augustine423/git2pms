package org.mdt.crewtaskmanagement.dto.material;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MaterialDto {
    private long id;
    private String serialNo;
    private String name;
    private String type;
    private String description;
    private String status;         // Should be string enum (e.g. "AVAILABLE")
    private String manufacturer;
    private long price;
    private String condition;
    private String supplierInfo;
    private String receivedDate;
    private int lifeTimeHours;     // was named "lifeTime" — renamed to match entity
    private String expectedExpiryDate;
    private String locationCode;
    private Long shipId;
}
