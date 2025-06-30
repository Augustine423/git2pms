package org.mdt.crewtaskmanagement.dto.task;

import java.util.List;

public record PageableTaskListDto<TaskDto>(
        List<TaskDto> tasks,
        int page,
        int size,
        int total
) {
    public int getTotalPages() {
        return (int) Math.ceil((double) total/ size);
    }
}
