package org.mdt.crewtaskmanagement.repository.entity;

import org.mdt.crewtaskmanagement.model.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Long> {


    @Query("SELECT t FROM TaskAssignment t " +
            "JOIN FETCH t.task tk " +
            "JOIN FETCH t.crew c " +
            "JOIN FETCH t.ship s " +
            "WHERE c.id = :crewId")
    List<TaskAssignment> findByCrewIdWithDetails(@Param("crewId") long crewId);
}
