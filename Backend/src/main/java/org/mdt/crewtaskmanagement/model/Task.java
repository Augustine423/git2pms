package org.mdt.crewtaskmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mdt.crewtaskmanagement.model.system.Component;
import org.mdt.crewtaskmanagement.model.type.TaskInterval;

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

    private String title;

    private String description;

    private String position;

    private boolean critical;

    @ManyToOne
    private Component component;

    @Enumerated(EnumType.STRING)
    private TaskInterval taskType;

    @Enumerated(EnumType.STRING)
    private TaskKind kind;

    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<MaintenanceLog> maintenanceLogs;

    @OneToMany(mappedBy = "task")
    private List<TaskAssignment> taskSchedules = new ArrayList<>();



    public enum TaskKind {
        PMS,SMS,CMS
    }



}
