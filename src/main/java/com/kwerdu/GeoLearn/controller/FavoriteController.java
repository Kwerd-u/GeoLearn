package com.kwerdu.GeoLearn.controller;

import com.kwerdu.GeoLearn.model.Region;
import com.kwerdu.GeoLearn.repository.UserRepository;
import com.kwerdu.GeoLearn.service.FavoriteService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final UserRepository userRepository;

    public FavoriteController(FavoriteService favoriteService, UserRepository userRepository) {
        this.favoriteService = favoriteService;
        this.userRepository = userRepository;
    }

    private int getCurrentUserId(Authentication auth) {
        return userRepository.findByNick(auth.getName())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"))
                .getId();
    }

    @GetMapping
    public List<Region> getAll(Authentication auth) {
        return favoriteService.getAll(getCurrentUserId(auth));
    }

    @PostMapping("/{regionId}")
    public void add(@PathVariable int regionId, Authentication auth) {
        favoriteService.add(getCurrentUserId(auth), regionId);
    }

    @DeleteMapping("/{regionId}")
    public void remove(@PathVariable int regionId, Authentication auth) {
        favoriteService.remove(getCurrentUserId(auth), regionId);
    }
}