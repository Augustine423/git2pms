package org.mdt.crewtaskmanagement.mapper;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.crew.CrewDto;
import org.mdt.crewtaskmanagement.model.Crew;
import org.mdt.crewtaskmanagement.model.type.CrewRank;
import org.mdt.crewtaskmanagement.model.type.Gender;
import org.mdt.crewtaskmanagement.model.type.Section;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
@RequiredArgsConstructor
public class CrewMapper {



    public static CrewDto toDto(Crew crew) {
        return CrewDto.builder()
                .id(crew.getId())
                .firstName(crew.getFirstName())
                .lastName(crew.getLastName())
                .email(crew.getEmail())
               // Never expose password in DTO
                .gender(crew.getGender() != null ? crew.getGender().name() : null)
                .phone(crew.getPhone())
                .birthDate(crew.getBirthDate() != null ? crew.getBirthDate().toString() : null)
                .joinedDate(crew.getJoinedDate() != null ? crew.getJoinedDate().toString() : null)
                .active(crew.isActive())
                .section(crew.getSection() != null ? crew.getSection().name() : null)
                .crewRank(crew.getCrewRank() != null ? crew.getCrewRank().name() : null)
                .nationality(crew.getNationality())
                .emergencyPhone(crew.getEmergencyPhone())
                .emergencyEmail(crew.getEmergencyEmail())
                .photoUrl(crew.getPhotoUrl())
                .certificates(crew.getCertificates())
                .certificatesExpiry(crew.getCertificatesExpiry() != null ? crew.getCertificatesExpiry().toString() : null)
                .licenseNo(crew.getLicenseNo())
                .licenseExpiry(crew.getLicenseExpiry())
                .build();
    }


    public static Crew fromDto(CrewDto dto) {
        Crew crew = new Crew();
        crew.setFirstName(dto.getFirstName());
        crew.setLastName(dto.getLastName());
        crew.setEmail(dto.getEmail());
        crew.setPassword(dto.getPassword());
        crew.setGender(dto.getGender() != null ? Gender.valueOf(dto.getGender()) : null);
        crew.setPhone(dto.getPhone());
        crew.setBirthDate(LocalDate.parse(dto.getBirthDate()));
        crew.setJoinedDate(LocalDate.parse(dto.getJoinedDate()));
        crew.setActive(dto.isActive());
        crew.setSection(dto.getSection() != null ? Section.valueOf(dto.getSection()) : null);
        crew.setCrewRank(dto.getCrewRank() != null ? CrewRank.valueOf(dto.getCrewRank()) : null);
        crew.setNationality(dto.getNationality());
        crew.setEmergencyPhone(dto.getEmergencyPhone());
        crew.setEmergencyEmail(dto.getEmergencyEmail());
        crew.setPhotoUrl(dto.getPhotoUrl());
        crew.setCertificates(dto.getCertificates());
        crew.setCertificatesExpiry(LocalDate.parse(dto.getCertificatesExpiry()));
        crew.setLicenseNo(dto.getLicenseNo());
        crew.setLicenseExpiry(dto.getLicenseExpiry());
        return crew;
    }
}
