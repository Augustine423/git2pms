package org.mdt.crewtaskmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private UserRole roleName;

    public enum UserRole {
        LEADER,
        ADMIN,              // Full system access
        SUPERINTENDENT,     // Shore-side user with authority over ships
        SHIP_MANAGER,       // Can manage tasks & schedules on the ship
        CREW,               // Default crew access - log maintenance, view tasks
        ENGINEER,           // Can update engine tasks and logs
        OFFICER,            // Deck officer role
        AUDITOR,            // Can only view reports/logs
        INSPECTOR,          // Can verify tasks, approve
        APPROVER,           // Can approve reports/tasks
        COMPANY_ADMIN       // Belongs to Company entity
    }

}
