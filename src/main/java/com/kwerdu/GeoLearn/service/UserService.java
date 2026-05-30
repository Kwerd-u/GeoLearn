package com.kwerdu.GeoLearn.service;

import com.kwerdu.GeoLearn.model.User;
import com.kwerdu.GeoLearn.model.enums.Role;
import com.kwerdu.GeoLearn.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.kwerdu.GeoLearn.security.JWTService;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTService jwtService;


    public record AuthResponse(String token) {}

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public void register(String nick, String password){
        if (userRepository.findByNick(nick).isPresent()) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        String passwordHash = passwordEncoder.encode(password);
        User user = new User();
        user.setNick(nick);
        user.setPassword(passwordHash);
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    public AuthResponse login(String login, String password){
        User user = userRepository.findByNick(login)
                .orElseThrow(() -> new RuntimeException("Пользователя с таким именем не существует"));
        if (passwordEncoder.matches(password, user.getPassword())){
            String token = jwtService.generateToken(user.getNick(), user.getRole().name());
            return new AuthResponse(token);
        }
        else {
            throw new RuntimeException("Неправильный пароль или логин");
        }
    }

    public void makeAdmin(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }

    public User getById(int id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }

    public User getByNick(String nick){
        return userRepository.findByNick(nick)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }
}
