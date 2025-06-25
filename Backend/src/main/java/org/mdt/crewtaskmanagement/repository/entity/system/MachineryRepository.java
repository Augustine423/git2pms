package org.mdt.crewtaskmanagement.repository.entity.system;

import org.mdt.crewtaskmanagement.model.system.Machinery;
import org.mdt.crewtaskmanagement.repository.BaseRepository;

import java.util.Optional;

public interface MachineryRepository extends BaseRepository<Machinery, Long> {

    Optional<Machinery> findByMachineryName(String machineryName);
}
