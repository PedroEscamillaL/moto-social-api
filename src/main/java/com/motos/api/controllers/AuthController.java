package com.motos.api.controllers;

import com.motos.api.config.JwtUtil;
import com.motos.api.models.User;
import com.motos.api.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody User user
    ) {

        Optional<User> existingUser =
                userRepository.findByUsername(
                        user.getUsername()
                );

        if (existingUser.isPresent()) {

            return ResponseEntity
                    .badRequest()
                    .body("El usuario ya existe");
        }

        userRepository.save(user);

        return ResponseEntity.ok("Usuario creado");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> body
    ) {

        String username = body.get("username");
        String password = body.get("password");

        Optional<User> user =
                userRepository.findByUsername(username);

        if (
                user.isPresent() &&
                user.get().getPassword().equals(password)
        ) {

            String token =
                    JwtUtil.generateToken(username);

         return ResponseEntity.ok(
        Map.of(
                "token", token,
                "id", user.get().getId(),
                "username", user.get().getUsername()
        )
);
        }

        return ResponseEntity
                .badRequest()
                .body("Credenciales incorrectas");
    }
}