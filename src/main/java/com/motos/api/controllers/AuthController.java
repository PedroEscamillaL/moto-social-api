package com.motos.api.controllers;

import com.motos.api.config.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> body
    ) {

        String username = body.get("username");
        String password = body.get("password");

        if (
                username.equals("admin") &&
                password.equals("12345678")
        ) {

            String token = JwtUtil.generateToken(username);

            return ResponseEntity.ok(
                    Map.of(
                            "token", token
                    )
            );
        }

        return ResponseEntity
                .badRequest()
                .body("Credenciales incorrectas");
    }
}