package com.kwerdu.GeoLearn.service;

import com.kwerdu.GeoLearn.model.Favorite;
import com.kwerdu.GeoLearn.repository.FavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository){
        this.favoriteRepository = favoriteRepository;
    }

    public List<Favorite> getAll(){
        return favoriteRepository.findAll();
    }

    public Favorite getById(int id){
        return favoriteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Избранный регион не найден"));
    }

    public void create(Favorite favorite){
        favoriteRepository.save(favorite);
    }

    public void update(int id, Favorite favorite){
        favorite.setId(id);
        favoriteRepository.save(favorite);
    }

    public void delete(Favorite favorite){
        favoriteRepository.delete(favorite);
    }

    public void deleteById(int id){
        favoriteRepository.deleteById(id);
    }
}
