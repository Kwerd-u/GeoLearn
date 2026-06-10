package com.kwerdu.GeoLearn.repository;

import com.kwerdu.GeoLearn.model.RegionFeatureValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionFeatureValueRepository extends JpaRepository<RegionFeatureValue, Integer> {
    List<RegionFeatureValue> findByFeatureDefinition_Id(int id);
    RegionFeatureValue findByRegion_IdAndFeatureDefinition_Id(int regionId, int featureDefinitionId);
    List<RegionFeatureValue> findByRegion_Id(int regionId);
}
