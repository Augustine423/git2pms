package org.mdt.crewtaskmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor

@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    @Column(unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles=new HashSet<>();

    public User() {

    }

    public void addRole(Role role){
        roles.add(role);
    }
}
