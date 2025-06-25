package org.mdt.crewtaskmanagement.service.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.crew.CrewDto;
import org.mdt.crewtaskmanagement.exception.ServiceBaseException;
import org.mdt.crewtaskmanagement.mapper.CrewMapper;
import org.mdt.crewtaskmanagement.model.Crew;
import org.mdt.crewtaskmanagement.model.Role;
import org.mdt.crewtaskmanagement.param.CrewParam;
import org.mdt.crewtaskmanagement.repository.entity.CrewRepository;
import org.mdt.crewtaskmanagement.repository.entity.RoleRepository;
import org.mdt.crewtaskmanagement.service.CrewService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CrewServiceImpl implements CrewService {
    private final CrewRepository crewRepository;
    private final RoleRepository roleDao;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public CrewDto registerCrew(CrewDto dto) throws ServiceBaseException {

            if (dto.getUserType() == null || dto.getUserType().isBlank()) {
                throw new ServiceBaseException(List.of("User type cannot be null or empty"));
            }

            Crew crew = CrewMapper.fromDto(dto);
            crew.setPassword(passwordEncoder.encode(dto.getPassword()));

            String roleName = switch (dto.getUserType().toLowerCase()) {
                case "firstleader" -> "FIRST_LEADER";
                case "secondleader" -> "SECOND_LEADER";
                case "thirdleader" -> "THIRD_LEADER";
                case "worker" -> "WORKER";
                default -> throw new ServiceBaseException(List.of("Unknown user type: " + dto.getUserType()));
            };

            Role role = roleRepository.findByRoleName(roleName)
                    .orElseThrow(() -> new ServiceBaseException(List.of("Role not found: " + roleName)));

            crew.addRole(role);
            Crew savedCrew = crewRepository.save(crew);

            return CrewMapper.toDto(savedCrew);
    }

    @Override
    public CrewDto updateCrew(CrewDto dto) {
        Crew crew = crewRepository.findById(dto.getId()).orElseThrow();
        Crew c = CrewMapper.fromDto(dto);
        c.setId(crew.getId());
        return CrewMapper.toDto(crewRepository.saveAndFlush(c));
    }


    @Override
    public CrewDto getCrewById(long id) {
        return CrewMapper.toDto(crewRepository.findById(id).orElseThrow());
    }

    @Override
    public List<CrewDto> getAllCrews() {
        return crewRepository.findAll().stream().map(CrewMapper::toDto).collect(Collectors.toList());
    }



    @Override
    public void deleteCrew(long id) {
        crewRepository.deleteById(id);
    }

    @Override
    public List<CrewDto> getCrewsForAssignments() {
        return crewRepository.findAvailableCrewsForAssignment().stream().map(CrewMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<CrewDto> getAllCrewsByShipId(long shipId) {
        return crewRepository.findCrewsByShipId(shipId).stream().map(CrewMapper::toDto).collect(Collectors.toList());
    }
@Override
    public List<CrewDto> search(CrewParam param){
        Function<CriteriaBuilder, CriteriaQuery<Crew>> query = cb -> {
            var cq = cb.createQuery(Crew.class);
            var root = cq.from(Crew.class);

             cq.select(root);
             cq.where(param.where(cb,root));
             cq.orderBy(cb.asc(root.get("firstName")));

             return cq;
        };

        return crewRepository.search(query).stream().map(CrewMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public int getAllCrewsCount() {
        return crewRepository.getAllCrewsCount();
    }

    @Override
    public int getAllActiveCrewCount() {
        return crewRepository.getAllActiveCrewCount();
    }


}
