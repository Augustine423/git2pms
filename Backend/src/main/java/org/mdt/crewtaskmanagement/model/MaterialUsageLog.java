package org.mdt.crewtaskmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MaterialUsageLog {
    @EmbeddedId
    private MaterialUsageLogId id;
    private LocalDateTime endAt;
    private String usedBy;
    private String remarks;
}
