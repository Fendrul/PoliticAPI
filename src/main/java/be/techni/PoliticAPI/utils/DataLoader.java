package be.techni.PoliticAPI.utils;

import be.techni.PoliticAPI.models.entities.*;
import be.techni.PoliticAPI.models.enums.ArgumentState;
import be.techni.PoliticAPI.repositories.*;
import be.techni.PoliticAPI.services.impl.ArgumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {
    private final ArgumentRepository argumentRepo;
    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;
    private final RoleRepository roleRepo;
    private final PrivilegeRepository privilegeRepo;
    private final PasswordEncoder passwordEncoder;
    private final ArgumentService argumentService;

    @Autowired
    public DataLoader(ArgumentRepository argumentRepo, UserRepository userRepo, CategoryRepository categoryRepo, RoleRepository roleRepo, PrivilegeRepository privilegeRepo, PasswordEncoder passwordEncoder, ArgumentService argumentService) {
        this.argumentRepo = argumentRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
        this.roleRepo = roleRepo;
        this.privilegeRepo = privilegeRepo;
        this.passwordEncoder = passwordEncoder;
        this.argumentService = argumentService;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public void run(ApplicationArguments args) {
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

//        Categories
        categoryRepo.save(new Category("Politique"));
        categoryRepo.save(new Category("Santé"));
        categoryRepo.save(new Category("Economie"));
        categoryRepo.save(new Category("Environnement"));
        categoryRepo.save(new Category("Sécurité"));
        categoryRepo.save(new Category("Education"));
        categoryRepo.save(new Category("Culture"));
        categoryRepo.save(new Category("Sport"));
        categoryRepo.save(new Category("Autre"));

//        Arguments
        Argument argument = new Argument();
        argument.setAuthor(user);
        argument.setTitle("title test");
        argument.setDescription("description test");
        argument.setState(ArgumentState.VALIDATED);
        argumentService.addCategoriesNamesToArgument(argument, 1);
        argumentRepo.save(argument);

        argument = new Argument();
        argument.setAuthor(user);
        argument.setTitle("title test answer");
        argument.setDescription("description test answer");
        argument.setState(ArgumentState.VALIDATED);
        argument.setAnswerTo(argumentRepo.findById(1L).get());
        argumentService.addCategoriesNamesToArgument(argument, 1, 5);
        argumentRepo.save(argument);

        argument = new Argument();
        argument.setAuthor(user);
        argument.setTitle("La vélocité des poules");
        argument.setDescription("La vélocité des poules est un phénomène qui se produit lorsque les poules sont en train de se reproduire. Ce phénomène est très dangereux pour les poules et les humains. Il est donc important de le prévenir.");
        argument.setState(ArgumentState.VALIDATED);
        argumentRepo.save(argument);
        argumentService.addCategoriesNamesToArgument(argument, 2, 5, 7);

        argument = new Argument();
        argument.setAuthor(user);
        argument.setTitle("Pourquoi les œufs au plat devraient être proscrits");
        argument.setDescription("Les œufs au plat sont un danger pour la santé publique. En effet, ils sont très gras et peuvent provoquer des maladies cardio-vasculaires. Il est donc important de les interdire.");
        argument.setState(ArgumentState.VALIDATED);
        argumentService.addCategoriesNamesToArgument(argument, 1, 4, 7);
        argumentRepo.save(argument);

        argument = new Argument();
        argument.setAuthor(user);
        argument.setTitle("L'émancipation des femmes dans notre société");
        argument.setDescription("Les femmes sont encore trop souvent considérées comme des objets dans notre société. Il est donc important de les émanciper afin qu'elles puissent s'épanouir pleinement.");
        argument.setState(ArgumentState.PENDING);
        argumentService.addCategoriesNamesToArgument(argument, 1, 3, 5, 7);
        argumentRepo.save(argument);

        
    }

    private Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepo.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepo.save(privilege);
        }
        return privilege;
    }

    private void createRoleIfNotFound(String name, Set<Privilege> privileges) {
        roleRepo.findByName(name)
                .orElseGet(() -> {
                    Role newRole = new Role(name);
                    newRole.setPrivileges(privileges);
                    return roleRepo.save(newRole);
                });
    }
}
