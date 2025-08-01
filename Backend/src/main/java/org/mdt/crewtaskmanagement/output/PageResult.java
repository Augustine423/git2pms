package org.mdt.crewtaskmanagement.output;

import java.util.List;

public record PageResult<T>(
        List<T> contents,
        long totalItems,
        int totalPages,
        int currentPage
) {
    // Optional: remove getTotalPages() because it's already passed as a constructor argument
}
