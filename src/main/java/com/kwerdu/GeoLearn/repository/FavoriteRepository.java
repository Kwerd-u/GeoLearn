package com.kwerdu.GeoLearn.repository;

import com.kwerdu.GeoLearn.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
}
