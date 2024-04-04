package com.pixelpunch.vaultify.web.controllers;

import com.pixelpunch.vaultify.core.service.implementations.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pixelpunch.vaultify.core.component.RequestIP;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.getOrDefault("email", "example@gmail.com");
        String password = requestBody.getOrDefault("password", "12345678");

        String token = userService.login(email, password);
        if (token != null) {
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String email, @RequestParam String code, @RequestIP String ip) {
        String token = userService.verify(email, code);
        if (token != null) {
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid verification code", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@RequestParam String email) {
        userService.sendPasswordResetCode(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestParam String email, @RequestParam String code, @RequestParam String newPassword) {
        userService.resetPassword(email, code, newPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
