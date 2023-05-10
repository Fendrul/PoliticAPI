package be.techni.PoliticAPI.controllers;

import be.techni.PoliticAPI.models.forms.LoginForm;
import be.techni.PoliticAPI.models.forms.RegistrationForm;
import be.techni.PoliticAPI.services.impl.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthenticationManager authentication;
    private final AuthService authService;

    @Autowired
    public AuthController(AuthenticationManager authentication, AuthService authService) {
        this.authentication = authentication;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationForm form, BindingResult result) {
        if (result.hasErrors())
            return ResponseEntity.badRequest().body(result.getAllErrors());

        return authService.createUser(form, result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginForm form, BindingResult result) {
        if (result.hasErrors())
            return ResponseEntity.badRequest().body(result.getAllErrors());

        return authService.loginUser(form, result);
    }
}
