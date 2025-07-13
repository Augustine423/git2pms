package org.mdt.crewtaskmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String serialNo;

    private String name;
    private String description;
    private String type;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private MaterialStatus status;

    private String manufacturer;
    private long price;

    @Column(name = "material_condition")
    private String condition;

    private String supplierInfo;

    private LocalDate receivedDate;
    private int lifeTimeHours;
    private LocalDate expectedExpiryDate;

    private String locationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ship ship;

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialReportRequest> materialReportRequest = new ArrayList<>();

    public enum MaterialStatus {
        AVAILABLE, IN_USE, EXPIRED, RESERVED, DISCARDED
    }
}
