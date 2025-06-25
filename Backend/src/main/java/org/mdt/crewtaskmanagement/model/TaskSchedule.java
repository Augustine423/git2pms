package org.mdt.crewtaskmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TaskSchedule {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Task task;
    private LocalDate nextDue;
    private String frequency;
    private int repeatCount;
}
