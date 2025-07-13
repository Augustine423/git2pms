package org.mdt.crewtaskmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mdt.crewtaskmanagement.model.type.CrewRank;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approver_id")
    private Crew approver;

    @Column(name = "approval_rank")
    @Enumerated(EnumType.STRING)
    private CrewRank rank;

    private LocalDateTime approvalTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    private ReportRequest reportRequest;
}
