package org.mdt.crewtaskmanagement.service;

import org.mdt.crewtaskmanagement.model.system.Component;

import java.util.List;

public interface IComponentService {
    List<String> getAllComponentNames();
    Component getComponent(String componentName);
}
