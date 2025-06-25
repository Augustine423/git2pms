package org.mdt.crewtaskmanagement.security;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.model.User;
import org.mdt.crewtaskmanagement.repository.entity.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CrewUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User crew =  userRepository.findByEmail(username);
      if(crew == null) {
          throw new UsernameNotFoundException(username);
      }
        return new org.springframework.security.core.userdetails.User(
                crew.getEmail(),
                crew.getPassword(), // must be encoded!
                crew.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
                        .collect(Collectors.toList())
        );
    }
}
