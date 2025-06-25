package org.mdt.crewtaskmanagement.model;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class MaterialUsageLogId implements Serializable {
    private long id;
    private LocalDateTime usedAt;
    private int shipId;

    public MaterialUsageLogId(long id, LocalDateTime usedAt, int shipId) {
        this.id = id;
        this.usedAt = usedAt;
        this.shipId = shipId;
    }
}
