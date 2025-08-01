package org.mdt.crewtaskmanagement.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskMaterialUsed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_log_id", nullable = false)
    private MaintenanceLog maintenanceLog;

    private String materialName;
    private int quantityUsed;
    private String unit; // e.g., pcs, Ltr
}
