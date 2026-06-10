package com.kwerdu.GeoLearn.service;

import com.kwerdu.GeoLearn.model.Favorite;
import com.kwerdu.GeoLearn.model.Region;
import com.kwerdu.GeoLearn.model.User;
import com.kwerdu.GeoLearn.repository.FavoriteRepository;
import com.kwerdu.GeoLearn.repository.RegionRepository;
import com.kwerdu.GeoLearn.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final RegionRepository regionRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, UserRepository userRepository, RegionRepository regionRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.regionRepository = regionRepository;
    }

    public void add(int userId, int regionId) {
        if (favoriteRepository.existsByUser_IdAndRegion_Id(userId, regionId)) {
            return;
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new RuntimeException("Регион не найден"));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setRegion(region);
        favoriteRepository.save(favorite);
    }

    public void remove(int userId, int regionId) {
        favoriteRepository.deleteByUser_IdAndRegion_Id(userId, regionId);
    }

    public List<Region> getAll(int userId) {
        return favoriteRepository.findByUser_Id(userId)
                .stream()
                .map(Favorite::getRegion)
                .toList();
    }
}
