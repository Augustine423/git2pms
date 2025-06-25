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
public class Company extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(nullable = false)
    private String registeredBy;
    private String companyAddress;
    private String companyEmail;
    private LocalDate yearEstablished;
    private String companyPhone;
    private String companyFax;
    @OneToMany
    private List<Ship> ships = new ArrayList<>();

    public void addShip(Ship ship) {
        ship.setCompany(this);
        ships.add(ship);
    }
}
