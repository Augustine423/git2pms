package org.mdt.crewtaskmanagement.model;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private TaskAssignment taskAssignment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "verified_by_id")
    private Crew verifiedBy;

    private LocalDate nextDue;

    @Column(nullable = false)
    private String remarks;

    @Column(name = "logged_at", nullable = false)
    private LocalDate loggedAt;

    @Enumerated(EnumType.STRING)
    private MaintenanceStatus status;

    @NotNull
    private LocalDate lastWork;

    @Builder.Default
    @OneToMany(mappedBy = "maintenanceLog", fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    private List<TaskCompletionImage> images = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "maintenanceLog", fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    private List<TaskMaterialUsed> materialsUsed = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.loggedAt = LocalDate.now();
    }

//not sure may be, will be added later...
public enum MaintenanceStatus {
    COMPLETED, PENDING, FAILED, OVERDUE
}

 public void addImage(TaskCompletionImage image) {
        image.setMaintenanceLog(this);
        images.add(image);
 }
    public void addImages(List<TaskCompletionImage> image) {

        if (images == null) {
            images = new ArrayList<>();
        }
        image.stream()
                .forEach(a -> a.setMaintenanceLog(this));
        images.addAll(image);
    }

 public void addMaterialUsed(TaskMaterialUsed materialUsed) {
        materialUsed.setMaintenanceLog(this);
        materialsUsed.add(materialUsed);

 }
    public void addMaterialsUsed(List<TaskMaterialUsed> materialUsed) {
        if(materialsUsed == null) {
            materialsUsed = new ArrayList<>();
        }
      materialUsed.stream()
              .forEach(a -> a.setMaintenanceLog(this));
      materialsUsed.addAll(materialUsed);

    }


}
