package org.mdt.crewtaskmanagement.utils;

import org.mdt.crewtaskmanagement.output.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GetEntitesWithPageable {
    public static <T,D> PageResult<D> getAllWithPageable(Pageable pageable, Page<T> page, Function<T, D> mapper) {
        List<D> dtoEntites ;
        if(page == null) {
            return null;
        }
        if (mapper != null && page != null) {
            dtoEntites = page.getContent().stream()
                    .map(mapper)
                    .collect(Collectors.toList());
        } else {
            dtoEntites = (List<D>) page.getContent();
        }

        return new PageResult<D>(
                dtoEntites,
                page.getTotalElements(),
                page.getTotalPages(),
                pageable.getPageNumber()
        );

    }

}
