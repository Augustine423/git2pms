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
public class MachGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String machGroupName;
    @OneToMany(mappedBy = "machGroup",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Machinery> Machineries = new ArrayList<Machinery>();

    public MachGroup(String machGroupName) {
        this.machGroupName = machGroupName;
    }

    public void addMachinery(Machinery machinery) {
        machinery.setMachGroup(this);
        Machineries.add(machinery);
    }
}
