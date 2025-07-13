package org.mdt.crewtaskmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mdt.crewtaskmanagement.model.system.Component;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    @Column(length = 10000)
    private String title;
    @Lob
    @Column(length = 10000)
    private String description;

    private String position;

    private boolean critical;

    @ManyToOne
    private Component component;

    private int intervalValue;
    // e.g. 6
    @Enumerated(EnumType.STRING)
    private IntervalUnit intervalUnit; // e.g. MONTHS

    @Enumerated(EnumType.STRING)
    private TaskKind kind;

    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<MaintenanceLog> maintenanceLogs = new ArrayList<>();

    @OneToMany(mappedBy = "task")
    private List<TaskAssignment> taskSchedules = new ArrayList<>();

    public enum TaskKind {
        PMS,SMS,CMS
    }
    public enum IntervalUnit {
        DAYS,
        WEEKS,
        MONTHS,
        YEARS,
        HOURS // for running hour–based
    }



}
