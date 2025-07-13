package org.mdt.crewtaskmanagement.repository.entity;

import org.mdt.crewtaskmanagement.model.ReportRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRequestRepository extends JpaRepository<ReportRequest, Long> {
//    @Query("select  rr FROM ReportRequest rr WHERE EXISTS (SELECT 1 FROM Approval a WHERE a.reportRequest.id = rr.id AND a.position = 'third') AND EXISTS (SELECT 1 FROM Approval a WHERE a.reportRequest.id = rr.id AND a.position = 'second') AND NOT EXISTS (SELECT 1 FROM Approval a WHERE a.reportRequest.id = rr.id AND a.position = 'first')")
//    Page<ReportRequest> getReportRequestsForFirstLeader(Pageable pageable);
//    @Query("SELECT rr FROM ReportRequest rr WHERE EXISTS (SELECT 1 FROM Approval a WHERE a.reportRequest.id = rr.id AND a.position = 'third') AND NOT EXISTS (SELECT 1 FROM Approval a WHERE a.reportRequest.id = rr.id AND a.position = 'second')")
//    Page<ReportRequest> getReportRequestsForSecondLeader(Pageable pageable);
//    @Query("SELECT rr FROM ReportRequest rr WHERE NOT EXISTS (SELECT 1 FROM Approval a WHERE a.reportRequest.id = rr.id AND a.position = 'third')")
//    Page<ReportRequest> getReportRequestsForThirdLeader(Pageable pageable);
//    @Query("""
//    SELECT rr
//    FROM ReportRequest rr
//    JOIN Approval a ON a.reportRequest.id = rr.id
//    WHERE rr.crew.id = ?1
//      AND a.position IN ('first','second','third')
//    GROUP BY rr.id
//    HAVING COUNT(DISTINCT a.position) = 3
//""")
//    Page<ReportRequest> getApprovedReportRequests(long crewId, Pageable pageable);

    ReportRequest findByTaskAssignmentId(long taskAssignmentId);





}
