package be.techni.PoliticAPI.utils;

import be.techni.PoliticAPI.models.entities.Argument;
import be.techni.PoliticAPI.models.entities.Privilege;
import be.techni.PoliticAPI.models.entities.Role;
import be.techni.PoliticAPI.models.entities.User;
import be.techni.PoliticAPI.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {
    private final ArgumentRepository argumentRepo;
    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;
    private final RoleRepository roleRepo;
    private final PrivilegeRepository privilegeRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(ArgumentRepository argumentRepo, UserRepository userRepo, CategoryRepository categoryRepo, RoleRepository roleRepo, PrivilegeRepository privilegeRepo, PasswordEncoder passwordEncoder) {
        this.argumentRepo = argumentRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
        this.roleRepo = roleRepo;
        this.privilegeRepo = privilegeRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Privileges
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        Set<Privilege> adminPrivileges = Set.of(readPrivilege, writePrivilege);
        createRoleIfNotFound("ADMIN", adminPrivileges);
        createRoleIfNotFound("USER", Set.of(readPrivilege));

//        Roles
        Role adminRole = roleRepo.findByName("ADMIN").get();
        Role userRole = roleRepo.findByName("USER").get();

//        Users
        User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("1234"));
        user.setRoles(Set.of(adminRole));
        user.setEnabled(true);
        userRepo.save(user);

        user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("1234"));
        user.setRoles(Set.of(userRole));
        user.setEnabled(true);
        userRepo.save(user);

//        Arguments
        Argument argument = new Argument();
        argument.setAuthor(user);
        argument.setTitle("title test");
        argument.setDescription("description test");
        argumentRepo.save(argument);

        argument = new Argument();
        argument.setAuthor(user);
        argument.setTitle("title test answer");
        argument.setDescription("description test answer");
        argument.setAnswerTo(argumentRepo.findById(1L).get());
        argumentRepo.save(argument);
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepo.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepo.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(String name, Set<Privilege> privileges) {
        Role role = roleRepo.findByName(name)
                .orElseGet(() -> {
                    Role newRole = new Role(name);
                    newRole.setPrivileges(privileges);
                    return roleRepo.save(newRole);
                });
        return role;
    }
}
