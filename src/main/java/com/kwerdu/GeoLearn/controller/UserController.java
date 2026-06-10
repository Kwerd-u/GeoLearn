package com.kwerdu.GeoLearn.controller;

import com.kwerdu.GeoLearn.dto.UserDto;
import com.kwerdu.GeoLearn.model.User;
import com.kwerdu.GeoLearn.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;



    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/makeadmin")
    public void makeAdmin(@PathVariable int id){
        userService.makeAdmin(id);
    }

    @GetMapping("/me")
    public UserDto getMe(Authentication authentication) {
        User user = userService.getByNick(authentication.getName());
        return new UserDto(user.getId(), user.getNick(), user.getRole().name());
    }

    @GetMapping
    public List<UserDto> getAll(){
        return userService.getAll();
    }
}
