package org.mdt.crewtaskmanagement.repository.entity.system;

import org.mdt.crewtaskmanagement.model.system.Component;
import org.mdt.crewtaskmanagement.model.system.MachGroup;
import org.mdt.crewtaskmanagement.model.system.Machinery;
import org.mdt.crewtaskmanagement.repository.BaseRepository;

import java.util.Optional;

public interface MachGroupRepository extends BaseRepository<MachGroup, Long> {



    Optional<MachGroup> findByMachGroupName(String name);


}
