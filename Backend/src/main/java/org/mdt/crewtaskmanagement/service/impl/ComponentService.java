package org.mdt.crewtaskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.model.system.Component;
import org.mdt.crewtaskmanagement.repository.entity.system.ComponentRepository;
import org.mdt.crewtaskmanagement.service.IComponentService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ComponentService implements IComponentService {
    private final ComponentRepository componentRepository;
    @Override
    public List<String> getAllComponentNames() {
        return List.of();
    }

    @Override
    public Component getComponent(String componentName) {
        return componentRepository.findByComponentName(componentName).orElse(null);
    }
}
