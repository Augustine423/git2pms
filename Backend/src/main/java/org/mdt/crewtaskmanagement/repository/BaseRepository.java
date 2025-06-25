package org.mdt.crewtaskmanagement.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.function.Function;
@NoRepositoryBean

public interface BaseRepository<T,ID> extends JpaRepository<T,ID> {
    <R> List<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc);

    <R> Page<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc,
                       Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc,
                       int page, int size);
}
