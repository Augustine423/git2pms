package org.mdt.crewtaskmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.repository.taskCompletion.TaskCompletionImageRepository;
import org.mdt.crewtaskmanagement.repository.taskCompletion.TaskMaterialUsedRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskCompletionServiceImpl {
    private final TaskMaterialUsedRepository materialUsedRepository;
    private final TaskCompletionImageRepository taskCompletionImageRepository;

}
