package org.mdt.crewtaskmanagement.repository.entity;

import org.mdt.crewtaskmanagement.model.ReportRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static org.mdt.crewtaskmanagement.model.ReportRequest.*;

public interface ReportRequestRepository extends JpaRepository<ReportRequest, Long> {


    //see reports which you(crew) sent for approval (pending)
    @Query("""
    select rr
    from CrewAssignment ca
    join ReportRequest rr on rr.crew.id = ca.crew.id
    where rr.crew.id = :crewId
      and rr.status = 'APPROVED'
""")
    Page<ReportRequest> findApprovedReportsByCrew(@Param("crewId") Long crewId, Pageable pageable);
//see reports which you(crew) sent for approval (pending)
@Query("""
    select rr
    from ReportRequest rr
    where rr.crew.id = :crewId
      and rr.status = 'PENDING'
""")
    Page<ReportRequest> findPendingReportsByCrew(@Param("crewId") Long crewId, Pageable pageable);

    //see reports which you(leader) have to approve (pending).
    @Query("""
    select rr
    from CrewAssignment ca
    join ReportRequest rr on rr.crew.id = ca.crew.id
    where ca.reportTo.id = :leaderId
      and rr.status = 'PENDING'
""")
    Page<ReportRequest> findPendingReportsByLeader(@Param("leaderId") Long leaderId, Pageable pageable);
//see reports which you(leader) approved.
    @Query("""
    select rr
    from CrewAssignment ca
    join ReportRequest rr on rr.crew.id = ca.crew.id
    where ca.reportTo.id = :leaderId
      and rr.status = 'APPROVED'
""")
    Page<ReportRequest> findApprovedReportsByLeader(@Param("leaderId") Long leaderId, Pageable pageable);









}
