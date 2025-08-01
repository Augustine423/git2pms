package org.mdt.crewtaskmanagement.service.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.Get;
import org.mdt.crewtaskmanagement.dto.crew.CrewDto;
import org.mdt.crewtaskmanagement.dto.crewAssignment.CrewAssignmentDto;
import org.mdt.crewtaskmanagement.exception.ServiceBaseException;
import org.mdt.crewtaskmanagement.mapper.CrewMapper;
import org.mdt.crewtaskmanagement.mapper.crew.CrewAssignmentMapper;
import org.mdt.crewtaskmanagement.model.Crew;
import org.mdt.crewtaskmanagement.model.Role;
import org.mdt.crewtaskmanagement.model.type.CrewRank;
import org.mdt.crewtaskmanagement.output.PageResult;
import org.mdt.crewtaskmanagement.param.CrewParam;
import org.mdt.crewtaskmanagement.repository.entity.CrewAssignmentRepository;
import org.mdt.crewtaskmanagement.repository.entity.CrewRepository;
import org.mdt.crewtaskmanagement.repository.entity.RoleRepository;
import org.mdt.crewtaskmanagement.repository.entity.UserRepository;
import org.mdt.crewtaskmanagement.service.ICrewService;
import org.mdt.crewtaskmanagement.utils.GetEntitesWithPageable;
import org.mdt.crewtaskmanagement.utils.RoleMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CrewServiceImpl implements ICrewService {
    private final CrewRepository crewRepository;
    private final CrewAssignmentRepository crewAssignmentRepository;
    private final RoleRepository roleDao;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CrewAssignmentMapper crewAssignmentMapper;
    private final UserRepository userRepository;

    @Override
    public CrewDto registerCrew(CrewDto dto) throws ServiceBaseException {

        Role.UserRole role = RoleMapper.mapCrewRankToRole(CrewRank.valueOf(dto.getCrewRank()));


        if (dto.getUserType() == null || dto.getUserType().isBlank()) {
                throw new ServiceBaseException(List.of("User type cannot be null or empty"));
            }

            Crew crew = CrewMapper.fromDto(dto);
            crew.setPassword(passwordEncoder.encode(dto.getPassword()));

            Role crewRole = roleRepository.findByRoleName(Role.UserRole.valueOf( role.toString()))
                    .orElseThrow(() -> new ServiceBaseException(List.of("Role not found: " + role)));

            crew.addRole(crewRole);
            Crew savedCrew = crewRepository.save(crew);

            return CrewMapper.toDto(savedCrew);
    }

    @Override
    public CrewDto updateCrew(CrewDto dto) {
        Crew crew = crewRepository.findById(dto.getId()).orElseThrow();
        Crew c = CrewMapper.fromDto(dto);
        c.setId(crew.getId());
        c.setPassword(passwordEncoder.encode(dto.getPassword()));
        return CrewMapper.toDto(crewRepository.saveAndFlush(c));
    }
    @Override
    public long getIdFromContext() throws ServiceBaseException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String email;
        if (principal instanceof org.springframework.security.core.userdetails.UserDetails userDetails) {
            email = userDetails.getUsername(); // username is usually email
        } else if (principal instanceof String str) {
            email = str; // Sometimes principal is just the username
        } else {
            throw new ServiceBaseException("Unable to extract user info from security context");
        }
        return userRepository.getIdByEmail(email).orElseThrow();

    }

    @Override
    public CrewDto getCrewById(long id) {
        return CrewMapper.toDto(crewRepository.findById(id).orElseThrow());
    }

    @Override
    public Crew getById(long id) {
        return crewRepository.findById(id).orElseThrow();
    }

    @Override
    public PageResult<CrewDto> getAllCrews(int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
         var crews  = crewRepository.findAll(pageable);
         return GetEntitesWithPageable.getAllWithPageable(pageable,crews,CrewMapper::toDto);
    }



    @Override
    public void deleteCrew(long id) {
        crewRepository.deleteById(id);
    }

    @Override
    public PageResult<CrewDto> getCrewsForAssignments(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Crew> crews = crewRepository.findAvailableCrewsForAssignment(pageable);
        return GetEntitesWithPageable.getAllWithPageable(pageable,crews,CrewMapper::toDto);
    }

    @Override
    public PageResult<CrewDto> getAllCrewsByShipId(long shipId,int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
        var crews = crewRepository.findCrewsByShipId(shipId,pageable);
        return GetEntitesWithPageable.getAllWithPageable(pageable,crews,CrewMapper::toDto);
    }

    @Override
    public PageResult<CrewDto> getCrewsWithPosition(String rank,int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var crews = crewRepository.getCrewWithPosition(CrewRank.valueOf(rank),pageable);
        return GetEntitesWithPageable.getAllWithPageable(pageable,crews,CrewMapper::toDto);
    }

    @Override
    public PageResult<CrewDto> getAllCrewsWithPositionByShipId(long shipId,String rank, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var crews = crewRepository.getCrewWithPositionByShipId(shipId, CrewRank.valueOf(rank),pageable);
        return GetEntitesWithPageable.getAllWithPageable(pageable,crews,CrewMapper::toDto);
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
    @Override
    public CrewAssignmentDto addCrewAssignment(CrewAssignmentDto crewAssignmentDto) {
        var ca = crewAssignmentMapper.fromDto(crewAssignmentDto);
        var  c = crewAssignmentRepository.save(ca);
        return crewAssignmentMapper.toDto(c);
    }


}
