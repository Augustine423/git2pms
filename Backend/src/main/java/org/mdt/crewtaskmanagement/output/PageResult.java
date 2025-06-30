package org.mdt.crewtaskmanagement.output;

import java.util.List;

public record PageResult<T>(
        List<T> contents,
        long totalItems,
        int size,
        int currentPage
) {

    public int getTotalPages() {
        return (int) Math.ceil((double) totalItems / size);
    }
}
