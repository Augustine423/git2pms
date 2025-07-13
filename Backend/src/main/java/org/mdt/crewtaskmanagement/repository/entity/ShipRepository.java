package org.mdt.crewtaskmanagement.repository.entity;

import org.mdt.crewtaskmanagement.model.Ship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShipRepository extends JpaRepository<Ship, Long> {
    @Query("select s from Ship  s where s.company.id = :companyId ")
    Page<Ship> getShipByCompanyId(@Param("companyId")long companyId, Pageable pageable);
}
