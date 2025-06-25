package org.mdt.crewtaskmanagement.repository.entity.system;

import org.mdt.crewtaskmanagement.model.system.Component;
import org.mdt.crewtaskmanagement.repository.BaseRepository;

import java.util.Optional;

public interface ComponentRepository extends BaseRepository<Component, Long> {

    Optional<Component> findByComponentName(String componentName);

}
