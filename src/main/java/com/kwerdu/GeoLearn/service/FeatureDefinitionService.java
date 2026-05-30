package com.kwerdu.GeoLearn.service;

import com.kwerdu.GeoLearn.model.FeatureDefinition;
import com.kwerdu.GeoLearn.model.Region;
import com.kwerdu.GeoLearn.model.RegionFeatureValue;
import com.kwerdu.GeoLearn.repository.FeatureDefinitionRepository;
import com.kwerdu.GeoLearn.repository.RegionFeatureValueRepository;
import com.kwerdu.GeoLearn.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureDefinitionService {

    private final FeatureDefinitionRepository featureDefinitionRepository;
    private final RegionFeatureValueRepository featureValueRepository;
    private final RegionRepository regionRepository;

    public FeatureDefinitionService(FeatureDefinitionRepository featureDefinitionRepository, RegionFeatureValueRepository featureValueRepository, RegionRepository regionRepository) {
        this.featureDefinitionRepository = featureDefinitionRepository;
        this.featureValueRepository = featureValueRepository;
        this.regionRepository = regionRepository;
    }

    public void create(FeatureDefinition featureDefinition){
        featureDefinitionRepository.save(featureDefinition);
        for (Region region : regionRepository.findAll()){
            RegionFeatureValue regionFeatureValue = new RegionFeatureValue();
            regionFeatureValue.setRegion(region);
            regionFeatureValue.setFeatureDefinition(featureDefinition);
            regionFeatureValue.setValue("");
            featureValueRepository.save(regionFeatureValue);
        }
    }

    public List<FeatureDefinition> getAll(){
        return featureDefinitionRepository.findAll();
    }

    public List<RegionFeatureValue> getAllByFeatureDefinitionId(int id){
        return featureValueRepository.findByFeatureDefinition_Id(id);
    }

}
