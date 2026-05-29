package com.kwerdu.GeoLearn.repository;

import com.kwerdu.GeoLearn.model.RegionFeatureValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionFeatureValueRepository extends JpaRepository<RegionFeatureValue, Integer> {
}
