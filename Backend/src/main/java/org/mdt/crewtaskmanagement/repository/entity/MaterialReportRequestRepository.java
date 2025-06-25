package org.mdt.crewtaskmanagement.repository.entity;

import org.mdt.crewtaskmanagement.model.Material;
import org.mdt.crewtaskmanagement.model.MaterialReportRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaterialReportRequestRepository extends JpaRepository<MaterialReportRequest, Long> {
    @Query("select m from MaterialReportRequest mrr join Material m on mrr.material.id = m.id where mrr.reportRequest.id = :reportRequestId")
    List<Material> getMaterialsUsedInReportRequestByReportRequestId(@Param("reportRequestId") Long reportRequestId);



}
