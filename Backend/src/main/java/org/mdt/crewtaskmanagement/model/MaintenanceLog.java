package org.mdt.crewtaskmanagement.model;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@RequiredArgsConstructor

public class MaintenanceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Task task;

   @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crew_id", nullable = false)
    private Crew duty;

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

    @Column(length = 1000)
    private String mediaUrls;

    @PrePersist
    public void prePersist() {
        this.loggedAt = LocalDate.now();
    }



//not sure may be, will be added later...
public enum MaintenanceStatus {
    COMPLETED, PENDING, FAILED, OVERDUE
}


}
