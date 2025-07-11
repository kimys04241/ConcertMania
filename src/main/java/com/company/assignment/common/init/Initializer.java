package com.company.assignment.common.init;

import com.company.assignment.common.util.PasswordUtil;
import com.company.assignment.user.domian.entity.Role;
import com.company.assignment.user.domian.entity.User;
import com.company.assignment.user.repository.RoleRepository;
import com.company.assignment.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class Initializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        Role roleAdmin = Role.builder().name("admin").build();
        Role roleUser = Role.builder().name("user").build();
        List<Role> roles = List.of(roleAdmin, roleUser);
        roleRepository.saveAll(roles);

        User adminUser = User.builder().userId("admin").password("password").name("name").build();
        adminUser.getRoles().add(roleRepository.findByName("admin").orElseThrow());
        userRepository.save(adminUser);
    }

}
