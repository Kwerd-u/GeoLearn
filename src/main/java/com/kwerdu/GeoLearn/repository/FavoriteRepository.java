package com.kwerdu.GeoLearn.repository;

import com.kwerdu.GeoLearn.model.Favorite;
import com.kwerdu.GeoLearn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUser(User user);
}
