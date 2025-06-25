package org.mdt.crewtaskmanagement.repository.entity;

import org.mdt.crewtaskmanagement.model.Approval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApprovalRepository extends JpaRepository<Approval, Long> {
    @Query("select  a from Approval a where a.reportRequest.id = :reportRequestId")
    public List<Approval> getApprovalsByReportRequestId(@Param("reportRequestId") long reportRequestId);
}
