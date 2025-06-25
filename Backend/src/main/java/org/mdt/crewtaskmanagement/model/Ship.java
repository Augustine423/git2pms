package org.mdt.crewtaskmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ship extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String imoNumber;
    private String flag;
    private String type;
    private LocalDate yearBuilt;
    private String mmsi;
    private String shipOffice;
    @ManyToOne
    private Company company;
    @OneToMany(mappedBy = "ship")
    private List<TaskAssignment> taskSchedules;
    @OneToMany(mappedBy = "ship")
    private List<Material> materials = new ArrayList<>();



}
