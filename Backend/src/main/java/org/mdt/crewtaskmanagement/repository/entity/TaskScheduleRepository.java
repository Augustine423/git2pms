package org.mdt.crewtaskmanagement.repository.entity;

import org.mdt.crewtaskmanagement.model.TaskSchedule;
import org.mdt.crewtaskmanagement.repository.BaseRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskScheduleRepository  extends BaseRepository<TaskSchedule, Long> {

    Optional<List<TaskSchedule>> findByNextDueBefore(LocalDate nextDueBefore);
}
