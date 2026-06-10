package com.kwerdu.GeoLearn.repository;

import com.kwerdu.GeoLearn.model.Favorite;
import com.kwerdu.GeoLearn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUser_Id(int userId);
    @Transactional
    void deleteByUser_IdAndRegion_Id(int userId, int regionId);
    boolean existsByUser_IdAndRegion_Id(int userId, int regionId);
}
