package org.mdt.crewtaskmanagement.mapper.crew;

import org.mdt.crewtaskmanagement.dto.crew.CrewDto;
import org.mdt.crewtaskmanagement.model.Crew;
import org.mdt.crewtaskmanagement.model.type.CrewRank;
import org.mdt.crewtaskmanagement.model.type.Gender;

import java.time.LocalDate;

public class CrewMapper {

    public static CrewDto toDto(Crew crew) {
        return CrewDto.builder()
                .id(crew.getId())
                .firstName(crew.getFirstName())
                .lastName(crew.getLastName())
                .email(crew.getEmail())
                .gender(crew.getGender() != null ? crew.getGender().name() : null)
                .phone(crew.getPhone())
                .imgUrl(crew.getImgUrl())
                .birthDate(crew.getBirthDate() != null ? crew.getBirthDate().toString() : null)
                .joinedDate(crew.getJoinedDate() != null ? crew.getJoinedDate().toString() : null)
                .active(crew.isActive())
                .crewRank(crew.getCrewRank() != null ? crew.getCrewRank().name() : null)
                .nationality(crew.getNationality())
                .emergencyPhone(crew.getEmergencyPhone())
                .emergencyEmail(crew.getEmergencyEmail())
                .photoUrl(crew.getPhotoUrl())
                .certificates(crew.getCertificates())
                .certificatesExpiry(crew.getCertificatesExpiry() != null ? crew.getCertificatesExpiry().toString() : null)
                .licenseNo(crew.getLicenseNo())
                .licenseExpiry(crew.getLicenseExpiry() != null ? crew.getLicenseExpiry().toString() : null)
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
        crew.setImgUrl(dto.getImgUrl());
        crew.setBirthDate(dto.getBirthDate() != null ? LocalDate.parse(dto.getBirthDate()) : null);
        crew.setJoinedDate(dto.getJoinedDate() != null ? LocalDate.parse(dto.getJoinedDate()) : null);
        crew.setActive(dto.isActive());
        crew.setCrewRank(dto.getCrewRank() != null ? CrewRank.valueOf(dto.getCrewRank()) : null);
        crew.setNationality(dto.getNationality());
        crew.setEmergencyPhone(dto.getEmergencyPhone());
        crew.setEmergencyEmail(dto.getEmergencyEmail());
        crew.setPhotoUrl(dto.getPhotoUrl());
        crew.setCertificates(dto.getCertificates());
        crew.setCertificatesExpiry(dto.getCertificatesExpiry() != null ? LocalDate.parse(dto.getCertificatesExpiry()) : null);
        crew.setLicenseNo(dto.getLicenseNo());
        crew.setLicenseExpiry(dto.getLicenseExpiry() != null ? LocalDate.parse(dto.getLicenseExpiry()) : null);
        return crew;
    }
}
