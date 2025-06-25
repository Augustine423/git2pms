package org.mdt.crewtaskmanagement.dto.materialusagelog;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MaterialUsageLogDto {
    private long id;
    private String usedAt;
    private int shipId;
    private String endAt;
}
