package com.kwerdu.GeoLearn.service;

import com.kwerdu.GeoLearn.model.RegionFeatureValue;
import com.kwerdu.GeoLearn.repository.RegionFeatureValueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionFeatureValueService {

    private final RegionFeatureValueRepository regionFeatureValueRepository;

    public RegionFeatureValueService(RegionFeatureValueRepository regionFeatureValueRepository) {
        this.regionFeatureValueRepository = regionFeatureValueRepository;
    }

    public void update(int regionId, int featureDefinitionId, String value){
        RegionFeatureValue newRegionFeatureValue = regionFeatureValueRepository.findByRegion_IdAndFeatureDefinition_Id(regionId, featureDefinitionId);
        newRegionFeatureValue.setValue(value);
        regionFeatureValueRepository.save(newRegionFeatureValue);
    }

    public List<RegionFeatureValue> getByRegionId(int regionId) {
        return regionFeatureValueRepository.findByRegion_Id(regionId);
    }
}
