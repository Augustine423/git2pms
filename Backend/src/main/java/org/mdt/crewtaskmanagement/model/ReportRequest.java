package org.mdt.crewtaskmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class  ReportRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @ManyToOne
    private Crew crew;
    private String content;

    @OneToOne
    private TaskAssignment taskAssignment;
    private String reportType;
    private LocalDate requestDate;
    private boolean approved;
    @OneToMany(mappedBy = "reportRequest")
    private List<Approval> approvals;

    @OneToMany(mappedBy = "reportRequest", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<MaterialReportRequest> materialReportRequests= new ArrayList<>();

    public void addApproval(Approval approval) {

        approval.setReportRequest(this);
        approvals.add(approval);
    }
    public void addMaterialReportRequest(MaterialReportRequest materialReportRequest) {
        materialReportRequest.setReportRequest(this);
        materialReportRequests.add(materialReportRequest);
    }

}
