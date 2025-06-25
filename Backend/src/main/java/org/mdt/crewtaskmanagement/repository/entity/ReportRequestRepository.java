package org.mdt.crewtaskmanagement.repository.entity;

import org.mdt.crewtaskmanagement.model.ReportRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRequestRepository extends JpaRepository<ReportRequest, Long> {
    @Query("select  rr FROM ReportRequest rr WHERE EXISTS (SELECT 1 FROM Approval a WHERE a.reportRequest.id = rr.id AND a.position = 'third') AND EXISTS (SELECT 1 FROM Approval a WHERE a.reportRequest.id = rr.id AND a.position = 'second') AND NOT EXISTS (SELECT 1 FROM Approval a WHERE a.reportRequest.id = rr.id AND a.position = 'first')")
    List<ReportRequest> getReportRequestsForFirstLeader();
    @Query("SELECT rr FROM ReportRequest rr WHERE EXISTS (SELECT 1 FROM Approval a WHERE a.reportRequest.id = rr.id AND a.position = 'third') AND NOT EXISTS (SELECT 1 FROM Approval a WHERE a.reportRequest.id = rr.id AND a.position = 'second')")
    List<ReportRequest> getReportRequestsForSecondLeader();
    @Query("SELECT rr FROM ReportRequest rr WHERE NOT EXISTS (SELECT 1 FROM Approval a WHERE a.reportRequest.id = rr.id AND a.position = 'third')")
    List<ReportRequest> getReportRequestsForThirdLeader();
    @Query("""
    SELECT rr 
    FROM ReportRequest rr 
    JOIN Approval a ON a.reportRequest.id = rr.id 
    WHERE rr.crew.id = ?1 
      AND a.position IN ('first','second','third') 
    GROUP BY rr.id 
    HAVING COUNT(DISTINCT a.position) = 3
""")
    List<ReportRequest> getApprovedReportRequests(long crewId);

    ReportRequest findByTaskAssignmentId(long taskAssignmentId);





}
