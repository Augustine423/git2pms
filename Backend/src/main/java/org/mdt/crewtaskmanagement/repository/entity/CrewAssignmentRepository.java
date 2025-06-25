package org.mdt.crewtaskmanagement.repository.entity;

import org.mdt.crewtaskmanagement.model.CrewAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewAssignmentRepository extends JpaRepository<CrewAssignment, Long> {
}
