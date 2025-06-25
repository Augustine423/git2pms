package org.mdt.crewtaskmanagement.repository.entity;

import org.mdt.crewtaskmanagement.model.system.Component;
import org.mdt.crewtaskmanagement.repository.BaseRepository;

import java.util.Optional;

public interface ComponentRepositoryOne  extends BaseRepository<Component,Long> {

    Optional<Component> findByComponentName(String componentName);
}
