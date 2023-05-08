package be.techni.PoliticAPI.services.impl;

import be.techni.PoliticAPI.jwt.JwtProvider;
import be.techni.PoliticAPI.models.entities.Role;
import be.techni.PoliticAPI.models.entities.User;
import be.techni.PoliticAPI.models.forms.LoginForm;
import be.techni.PoliticAPI.models.forms.RegistrationForm;
import be.techni.PoliticAPI.repositories.RoleRepository;
import be.techni.PoliticAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class AuthService {
    private final UserRepository userRepo;
    private final JwtProvider jwtProvider;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepo, JwtProvider jwtProvider, RoleRepository roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.jwtProvider = jwtProvider;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> createUser(RegistrationForm form, BindingResult result) {
        User user = new User();

        if (userRepo.existsByName(form.getUsername())) {
            result.rejectValue("username", "user.username.invalid", "The username %s already exists".formatted(form.getUsername()));
        }

        Role role = roleRepo.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRoles().add(role);
        user.setUsername(form.getUsername());
        user.setPassword(passwordEncoder.encode(form.getPassword()));


        if (result.hasErrors())
            return ResponseEntity.badRequest().body(result.getAllErrors());

        userRepo.save(user);

        return setAuthorizationHeaderWithToken(user);
    }

    public ResponseEntity<?> loginUser(LoginForm form, BindingResult result) {
        User user = userRepo.findByUsername(form.getUsername())
                .orElseGet(() -> {
                    result.rejectValue("username", "login.email.invalid", "Username %s not found".formatted(form.getUsername()));
                    return null;
                });

        if (user != null) {
            if (passwordEncoder.matches(form.getPassword(), user.getPassword())) {
                return setAuthorizationHeaderWithToken(user);
            } else {
                result.rejectValue("password", "login.password.invalid", "Password is invalid");
            }
        }

        return ResponseEntity.badRequest().body(result.getAllErrors());
    }

    private ResponseEntity<?> setAuthorizationHeaderWithToken(User user) {
        String token = jwtProvider.createToken(user);
        System.out.println(token);
        return ResponseEntity.ok().header("Authorization", "Bearer " + token).build();
    }
}
