package com.garage_system.Controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.garage_system.Model.User;
import com.garage_system.Service.JWTService;
import com.garage_system.Service.UserService;

@RestController
public class AuthenticationController {

    private final UserService userService;
    private final JWTService jwtService;
    private final AuthenticationManager authManager;

    public AuthenticationController(UserService userService,
                          JWTService jwtService,
                          AuthenticationManager authManager) {
        this.userService = userService;
        this.jwtService  = jwtService;
        this.authManager = authManager;
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        userService.RegisterUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        /// This uses DaoAuthenticationProvider to validate credentials
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(user.getEmail());
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
