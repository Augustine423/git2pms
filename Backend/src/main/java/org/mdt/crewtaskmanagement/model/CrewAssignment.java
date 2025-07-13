package org.mdt.crewtaskmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mdt.crewtaskmanagement.model.type.Section;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CrewAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crew_id")
    private Crew crew;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ship_id")
    private Ship ship;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_by")
    private Crew assignedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_to")
    private Crew reportTo;

    @Enumerated(EnumType.STRING)
    private Section section;

    private boolean active;




    @Column(length = 512)
    private String remarks;

    private String position;
    private LocalDate startDate;
    private LocalDate endDate;
}
