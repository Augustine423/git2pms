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


    private LocalDate nextDue;

    @Column(nullable = false)
    private String remark;

    @NotNull
    private LocalDate lastWork;

    @Enumerated(EnumType.STRING)
    private MaintenanceStatus status;

//not sure may be, will be added later...
    public enum MaintenanceStatus {
        COMPLETED, PENDING, FAILED
    }


}
