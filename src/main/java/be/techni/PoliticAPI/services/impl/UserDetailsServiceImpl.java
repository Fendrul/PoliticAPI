package be.techni.PoliticAPI.services.impl;

import be.techni.PoliticAPI.models.entities.Privilege;
import be.techni.PoliticAPI.models.entities.Role;
import be.techni.PoliticAPI.models.entities.User;
import be.techni.PoliticAPI.repositories.RoleRepository;
import be.techni.PoliticAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl {
    private final UserRepository userRepository;
    private final RoleRepository roleRepo;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepo) {
        this.userRepository = userRepository;
        this.roleRepo = roleRepo;
    }

    public static Set<? extends GrantedAuthority> getAuthorities(User user) {

        return getGrantedAuthorities(getPrivileges(user.getRoles()));
    }

    /**
     * Get the privileges from the roles
     *
     * @param roles the roles of the user
     * @return the privileges of the user
     * @deprecated use user.getAuthorities instead
     */
    @Deprecated
    private static Set<String> getPrivileges(Set<Role> roles) {
        return roles.stream().map(Role::getPrivileges).flatMap(Set::stream).map(Privilege::getName).collect(Collectors.toSet());
    }

    private static Set<GrantedAuthority> getGrantedAuthorities(Set<String> privileges) {
        return privileges.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    //TODO vérifier le fonctionnement de cette méthode
    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();

        String hierarchy = "ROLE_ADMIN > ROLE_USER";
        roleHierarchy.setHierarchy(hierarchy);

        return roleHierarchy;
    }
}
