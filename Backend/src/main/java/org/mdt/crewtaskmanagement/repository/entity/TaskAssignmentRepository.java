package org.mdt.crewtaskmanagement.repository.entity;

import org.mdt.crewtaskmanagement.dto.task.CrewTaskDtoOutPut;
import org.mdt.crewtaskmanagement.model.TaskAssignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Long> {

    @Query("""
    SELECT new org.mdt.crewtaskmanagement.dto.task.CrewTaskDtoOutPut(
        t.id,
        c.id,
        s.name,
        tk.id,
        tk.title,
        tk.description,
        tk.intervalValue,
        tk.intervalUnit,
        t.assignedDate,
        t.deadlineDate,
        t.status,
        CASE WHEN ca.reportTo IS NULL THEN '' 
             ELSE CONCAT(COALESCE(ca.reportTo.firstName, ''), ' ', COALESCE(ca.reportTo.lastName, '')) 
        END
    )
    FROM TaskAssignment t
    JOIN t.task tk
    JOIN t.crew c
    JOIN t.ship s
    LEFT JOIN CrewAssignment ca ON ca.crew.id = c.id
    WHERE c.id = :crewId
    """)
    Page<CrewTaskDtoOutPut> findAllTasksByCrewId(@Param("crewId") long crewId, Pageable pageable);

    @Query("""
    SELECT new org.mdt.crewtaskmanagement.dto.task.CrewTaskDtoOutPut(
        t.id,
        c.id,
        s.name,
        tk.id,
        tk.title,
        tk.description,
        tk.intervalValue,
        tk.intervalUnit,
        t.assignedDate,
        t.deadlineDate,
        t.status,
        CASE WHEN r IS NULL THEN '' ELSE CONCAT(COALESCE(r.firstName, ''), ' ', COALESCE(r.lastName, '')) END
    )
    FROM TaskAssignment t
    JOIN t.task tk
    JOIN t.crew c
    JOIN t.ship s
    LEFT JOIN t.reportTo r
    WHERE c.id = :crewId AND t.status = org.mdt.crewtaskmanagement.model.TaskAssignment.AssignmentStatus.ACTIVE
    """)
    Page<CrewTaskDtoOutPut> findActiveTasksByCrewId(@Param("crewId") long crewId, Pageable pageable);

    @Query("""
    SELECT new org.mdt.crewtaskmanagement.dto.task.CrewTaskDtoOutPut(
        t.id,
        c.id,
        s.name,
        tk.id,
        tk.title,
        tk.description,
        tk.intervalValue,
        tk.intervalUnit,
        t.assignedDate,
        t.deadlineDate,
        t.status,
        CASE WHEN r IS NULL THEN '' ELSE CONCAT(COALESCE(r.firstName, ''), ' ', COALESCE(r.lastName, '')) END
    )
    FROM TaskAssignment t
    JOIN t.task tk
    JOIN t.crew c
    JOIN t.ship s
    LEFT JOIN t.reportTo r
    WHERE c.id = :crewId AND t.status = org.mdt.crewtaskmanagement.model.TaskAssignment.AssignmentStatus.COMPLETED
    """)
    Page<CrewTaskDtoOutPut> findCompletedTasksByCrewId(@Param("crewId") long crewId, Pageable pageable);

    @Query("""
    SELECT new org.mdt.crewtaskmanagement.dto.task.CrewTaskDtoOutPut(
        t.id,
        c.id,
        s.name,
        tk.id,
        tk.title,
        tk.description,
        tk.intervalValue,
        tk.intervalUnit,
        t.assignedDate,
        t.deadlineDate,
        t.status,
        CASE WHEN r IS NULL THEN '' ELSE CONCAT(COALESCE(r.firstName, ''), ' ', COALESCE(r.lastName, '')) END
    )
    FROM TaskAssignment t
    JOIN t.task tk
    JOIN t.crew c
    JOIN t.ship s
    LEFT JOIN t.reportTo r
    WHERE c.id = :crewId AND t.status != org.mdt.crewtaskmanagement.model.TaskAssignment.AssignmentStatus.COMPLETED
      AND t.deadlineDate < :now
    """)
    Page<CrewTaskDtoOutPut> findOverdueTasksByCrewId(
            @Param("crewId") long crewId,
            @Param("now") LocalDate now,
            Pageable pageable
    );

    @Query("""
    SELECT new org.mdt.crewtaskmanagement.dto.task.CrewTaskDtoOutPut(
        t.id,
        c.id,
        s.name,
        tk.id,
        tk.title,
        tk.description,
        tk.intervalValue,
        tk.intervalUnit,
        t.assignedDate,
        t.deadlineDate,
        t.status,
        CASE WHEN r IS NULL THEN '' ELSE CONCAT(COALESCE(r.firstName, ''), ' ', COALESCE(r.lastName, '')) END
    )
    FROM TaskAssignment t
    JOIN t.task tk
    JOIN t.crew c
    JOIN t.ship s
    LEFT JOIN t.reportTo r
    WHERE c.id = :crewId AND t.status != org.mdt.crewtaskmanagement.model.TaskAssignment.AssignmentStatus.UPCOMING
    """)
    Page<CrewTaskDtoOutPut> findUncompletedTasksByCrewId(
            @Param("crewId") long crewId,
            Pageable pageable
    );
}