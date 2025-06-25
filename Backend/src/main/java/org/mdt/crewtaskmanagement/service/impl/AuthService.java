package org.mdt.crewtaskmanagement.service.impl;
import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.dto.crew.AdminDto;
import org.mdt.crewtaskmanagement.dto.crew.CrewDto;
import org.mdt.crewtaskmanagement.jwt.JwtTokenProvider;
import org.mdt.crewtaskmanagement.mapper.CrewMapper;
import org.mdt.crewtaskmanagement.model.Admin;
import org.mdt.crewtaskmanagement.model.Crew;
import org.mdt.crewtaskmanagement.model.Role;
import org.mdt.crewtaskmanagement.repository.entity.AdminRepository;
import org.mdt.crewtaskmanagement.repository.entity.CrewRepository;
import org.mdt.crewtaskmanagement.repository.entity.RoleRepository;
import org.mdt.crewtaskmanagement.repository.entity.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleDao;
    private final AuthenticationManager authenticationManager;
    private  final JwtTokenProvider jwtTokenProvider;
    private final AdminRepository adminDao;
    private final CrewRepository crewRepository;

    public record LoginResponse(String token,long id,List<String> roles){}
    public LoginResponse login(String username, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        long userId = userDao.getIdByEmail(userDetails.getUsername()).orElseThrow();

        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return  new LoginResponse(jwtTokenProvider.generateToken(authentication),userId,roles) ;
    }
    @Transactional
    public String registerAdmin(AdminDto request) {
        Role adminRole = roleDao.findByRoleName("ADMIN")
                .orElseGet(() -> {
                    Role r = new Role();
                    r.setRoleName("ADMIN");
                    return roleDao.save(r);
                });

        Admin admin = new Admin();
        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setEducation(request.getEducation());
        admin.setEmail(request.getEmail());
        admin.addRole(adminRole);
        adminDao.save(admin);

        return "Admin registered successfully";
    }
    @Transactional
    public String registerCrewByAdmin(CrewDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Role userRole;
        switch (dto.getCrewRank().toLowerCase()) {
            case "first":
                userRole = roleDao.findByRoleName("FIRST_LEADER").orElseThrow();
                break;
            case "second":
                userRole = roleDao.findByRoleName("SECOND_LEADER").orElseThrow();
                break;
            case "third":
                userRole = roleDao.findByRoleName("THIRD_LEADER").orElseThrow();
                break;
            case "general":
                userRole = roleDao.findByRoleName("WORKER").orElseThrow();
                break;
            default:
                throw new IllegalArgumentException("Invalid crew rank: " + dto.getCrewRank());
        }

        // Ensure the Crew has an ID if you're updating


        // Make sure roles are properly initialized




        Crew crew = CrewMapper.fromDto(dto);
        crew.addRole(userRole);
        crewRepository.save(crew);

        return "Crew registered successfully by Admin";
    }
}
