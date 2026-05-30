package com.kwerdu.GeoLearn.controller;

import com.kwerdu.GeoLearn.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public record AuthRequest(String nick, String password) {}

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@RequestBody AuthRequest request) {
        userService.register(request.nick(), request.password());
    }

    @PostMapping("/login")
    public UserService.AuthResponse login(@RequestBody AuthRequest request) {
        return userService.login(request.nick(), request.password());
    }
}
