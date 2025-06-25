package org.mdt.crewtaskmanagement.model;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class CrewShipsId implements Serializable {
    private Long crewId;
    private Long shipId;

    public CrewShipsId(Long crewId, Long shipId) {
        this.crewId = crewId;
        this.shipId = shipId;
    }
}
