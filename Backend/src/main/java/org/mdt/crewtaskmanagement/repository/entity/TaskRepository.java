package org.mdt.crewtaskmanagement.repository.entity;

import org.mdt.crewtaskmanagement.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findAll (Pageable pageable);


}
