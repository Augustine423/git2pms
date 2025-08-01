package org.mdt.crewtaskmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TaskCompletionImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_log_id", nullable = false)
    private MaintenanceLog maintenanceLog;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private ImageType imageType;

    public enum ImageType {
        BEFORE, AFTER, DEFECT
    }
}
