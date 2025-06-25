package org.mdt.crewtaskmanagement.param;

import ch.qos.logback.core.util.StringUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.mdt.crewtaskmanagement.model.Crew;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public record CrewParam (String firstname, String lastname, String crewrank){
    public Predicate where(CriteriaBuilder cb, Root<Crew> root) {
        var predicates = new ArrayList<Predicate>();



        if(StringUtils.hasLength(firstname)){
            var firstNamePredicate = cb.like(cb.lower(root.get("firstName")), firstname.toLowerCase().concat("%"));
            predicates.add(firstNamePredicate);
        }
        if(StringUtils.hasLength(lastname)){
            var lastNamePredicate = cb.like(cb.lower(root.get("lastName")), lastname.toLowerCase().concat("%"));
            predicates.add(lastNamePredicate);
        }

        if(StringUtils.hasLength(crewrank)){
            var crewRankPredicate = cb.like(cb.lower(root.get("crewRank")),crewrank.toLowerCase().concat("%"));
            predicates.add(crewRankPredicate);
        }

        return cb.and(predicates.toArray(Predicate[]::new));


    }
}
