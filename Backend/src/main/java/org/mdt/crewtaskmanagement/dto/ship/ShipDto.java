package org.mdt.crewtaskmanagement.dto.ship;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class ShipDto {
    private long id;
    private String name;
    private String imoNumber;
    private long companyId;
    private String flag;
    private String type;
    private String yearBuilt;
    private String mmsi;
    private String shipOffice;
}
