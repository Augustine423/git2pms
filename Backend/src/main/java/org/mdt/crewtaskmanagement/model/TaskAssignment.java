package org.mdt.crewtaskmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TaskAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Task task;
    @ManyToOne
    private Crew crew;
    @ManyToOne
    private Ship ship;
    private LocalDate assignedDate;
    private LocalDate deadlineDate;
    private boolean completed = false;



}
