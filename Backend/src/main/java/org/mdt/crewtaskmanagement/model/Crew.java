package org.mdt.crewtaskmanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.mdt.crewtaskmanagement.model.type.CrewRank;
import org.mdt.crewtaskmanagement.model.type.Gender;
import org.mdt.crewtaskmanagement.model.type.Section;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "crew")
public class Crew  extends User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String phone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate joinedDate;
    private boolean active;
    @Enumerated(EnumType.STRING)
    private Section section;
    @Enumerated(EnumType.STRING)
    private CrewRank crewRank;
    private String nationality;
    private String emergencyPhone;
    private String emergencyEmail;
    private String photoUrl;
    private String certificates;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate certificatesExpiry;
    private String licenseNo;
    private String licenseExpiry;
    @OneToMany(mappedBy = "crew")
    private List<ReportRequest> reportRequests;
    @OneToMany(mappedBy = "crew")
    private List<TaskAssignment> taskSchedules = new ArrayList<>();
    @OneToMany(mappedBy = "crew", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CrewAssignment> crewAssignments = new ArrayList<>();



}
