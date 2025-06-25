package org.mdt.crewtaskmanagement;

import lombok.RequiredArgsConstructor;
import org.mdt.crewtaskmanagement.model.Admin;
import org.mdt.crewtaskmanagement.model.Role;
import org.mdt.crewtaskmanagement.repository.entity.AdminRepository;
import org.mdt.crewtaskmanagement.repository.entity.RoleRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
@RequiredArgsConstructor
@SpringBootApplication
public class CrewTaskManagementApplication {
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
            adminRole.setRoleName("ADMIN");
            roleRepository.save(adminRole);
            admin.addRole(adminRole);
            adminRepository.save(admin);

        };
    }


    @Bean
    @Transactional
    @Profile("dev")
    public ApplicationRunner runner() {
        return args -> {


            Role workerRole = new Role();
            workerRole.setRoleName("WORKER");
            roleRepository.save(workerRole);

            Role thirdleadeRole = new Role();
            thirdleadeRole.setRoleName("THIRD_LEADER");
            roleRepository.save(thirdleadeRole);

            Role firstleaderRole = new Role();
            firstleaderRole.setRoleName("FIRST_LEADER");
            roleRepository.save(firstleaderRole);

            Role secondleaderRole = new Role();
            secondleaderRole.setRoleName("SECOND_LEADER");
            roleRepository.save(secondleaderRole);

            Role captainRole = new Role();
            captainRole.setRoleName("CAPTAIN");
            roleRepository.save(captainRole);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(CrewTaskManagementApplication.class, args);
    }

}
