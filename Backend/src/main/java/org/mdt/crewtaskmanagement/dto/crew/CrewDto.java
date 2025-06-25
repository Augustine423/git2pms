package org.mdt.crewtaskmanagement.dto.crew;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
@Data
@Builder
public class CrewDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private String phone;
    private String birthDate;
    private String joinedDate;
    private boolean active;
    private String section;
    private String crewRank;
    private String nationality;
    private String emergencyPhone;
    private String emergencyEmail;
    private String photoUrl;
    private String certificates;
    private String certificatesExpiry;
    private String licenseNo;
    private String licenseExpiry;
    private String userType;
}
