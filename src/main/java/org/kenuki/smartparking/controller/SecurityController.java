package org.kenuki.smartparking.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.kenuki.smartparking.models.dtos.LoginDTO;
import org.kenuki.smartparking.models.dtos.RegisterDTO;
import org.kenuki.smartparking.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/security")
@AllArgsConstructor
public class SecurityController {
    private final UserService userService;
    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody @Valid RegisterDTO registerDTO) {
        return ResponseEntity.ok(userService.register(registerDTO));
    }
    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody @Valid LoginDTO loginDTO) {
        return ResponseEntity.ok(userService.login(loginDTO));
    }
}
