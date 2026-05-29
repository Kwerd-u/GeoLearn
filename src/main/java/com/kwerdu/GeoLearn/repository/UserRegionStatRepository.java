package com.kwerdu.GeoLearn.repository;

import com.kwerdu.GeoLearn.model.UserRegionStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegionStatRepository extends JpaRepository<UserRegionStat, Integer> {
}
