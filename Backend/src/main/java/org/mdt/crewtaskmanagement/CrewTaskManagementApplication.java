package org.mdt.crewtaskmanagement;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.mdt.crewtaskmanagement.config.FlywayConfig;
import org.mdt.crewtaskmanagement.model.Admin;
import org.mdt.crewtaskmanagement.model.Role;
import org.mdt.crewtaskmanagement.repository.entity.AdminRepository;
import org.mdt.crewtaskmanagement.repository.entity.RoleRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
@RequiredArgsConstructor
@SpringBootApplication
public class CrewTaskManagementApplication {
    private final FlywayConfig  config;
    private final RoleRepository roleRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    @Bean @Profile("dev")
    public ApplicationRunner init() {
        return args -> {
           Admin admin = new Admin();
           admin.setFirstName("Admin");
           admin.setLastName("User");
           admin.setEducation("Education");
           admin.setEmail("admin@gmail.com");
           admin.setPassword(passwordEncoder.encode("12345"));
            Role adminRole = new Role();
            adminRole.setRoleName(Role.UserRole.ADMIN);
            roleRepository.save(adminRole);
            admin.addRole(adminRole);
            adminRepository.save(admin);

        };
    }
    public static void main(String[] args) {
        SpringApplication.run(CrewTaskManagementApplication.class, args);
    }

}
