package org.mdt.crewtaskmanagement.dto.reportrequest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MaterialRequestDto {
    private String materialName;
    private int quantity;
}
