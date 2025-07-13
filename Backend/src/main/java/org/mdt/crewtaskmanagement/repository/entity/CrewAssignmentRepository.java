package org.mdt.crewtaskmanagement.repository.entity;

import org.mdt.crewtaskmanagement.model.Crew;
import org.mdt.crewtaskmanagement.model.CrewAssignment;
import org.mdt.crewtaskmanagement.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CrewAssignmentRepository extends JpaRepository<CrewAssignment, Long> {
    @Query("select ca.ship from CrewAssignment ca where ca.crew.id =:crewId")
    Optional<Ship> getShipByCrewId(@Param("crewId")Long crewId);
    @Query("select ca.reportTo from CrewAssignment ca where ca.crew.id=:crewId")
    Optional<Crew> findCrewReportTo(@Param("crewId")Long crewId);
}
