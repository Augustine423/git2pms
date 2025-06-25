package org.mdt.crewtaskmanagement.model.system;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Machinery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private MachGroup machGroup;
    @OneToMany(mappedBy = "machinery", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Component> components = new ArrayList<>();

    private String machineryName;

    public Machinery(String machineryName) {
        this.machineryName = machineryName;
    }

    public void addComponent(Component component) {
        component.setMachinery(this);
        components.add(component);
    }
}
