package org.mdt.crewtaskmanagement.param;

import ch.qos.logback.core.util.StringUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.mdt.crewtaskmanagement.model.Crew;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public record CrewParam(String firstname, String lastname, String crewrank) {

    public Predicate where(CriteriaBuilder cb, Root<Crew> root) {
        List<Predicate> predicates = new ArrayList<>();

        addPredicateIfNotEmpty(predicates, cb, root, "firstName", firstname);
        addPredicateIfNotEmpty(predicates, cb, root, "lastName", lastname);
        addPredicateIfNotEmpty(predicates, cb, root, "crewRank", crewrank);

        return cb.and(predicates.toArray(Predicate[]::new));
    }

    private void addPredicateIfNotEmpty(List<Predicate> predicates, CriteriaBuilder cb, Root<Crew> root, String fieldName, String fieldValue) {
        if (StringUtils.hasLength(fieldValue)) {
            Predicate predicate = cb.like(cb.lower(root.get(fieldName)), fieldValue.toLowerCase().concat("%"));
            predicates.add(predicate);
        }
    }


}
