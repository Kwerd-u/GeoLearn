package com.kwerdu.GeoLearn.service;

import com.kwerdu.GeoLearn.model.FeatureDefinition;
import com.kwerdu.GeoLearn.model.Region;
import com.kwerdu.GeoLearn.model.RegionFeatureValue;
import com.kwerdu.GeoLearn.model.enums.FederalDistrict;
import com.kwerdu.GeoLearn.repository.FeatureDefinitionRepository;
import com.kwerdu.GeoLearn.repository.RegionFeatureValueRepository;
import com.kwerdu.GeoLearn.repository.RegionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RegionService {
    private final RegionRepository regionRepository;
    private final FeatureDefinitionRepository featureDefinitionRepository;
    private final RegionFeatureValueRepository regionFeatureValueRepository;

    public RegionService(RegionRepository regionRepository, FeatureDefinitionRepository featureDefinitionRepository, RegionFeatureValueRepository regionFeatureValueRepository) {
        this.regionRepository = regionRepository;
        this.featureDefinitionRepository = featureDefinitionRepository;
        this.regionFeatureValueRepository = regionFeatureValueRepository;
    }

    public List<Region> getAll(int page, int size, String districts, String sortBy) {
        Sort sort = Sort.by("district".equals(sortBy) ? "federalDistrict" : "name");
        Pageable pageable = PageRequest.of(page, size, sort);
        if (districts != null && !districts.isEmpty()) {
            List<FederalDistrict> districtList = Arrays.stream(districts.split(","))
                    .map(FederalDistrict::valueOf)
                    .toList();
            return regionRepository.findByFederalDistrictIn(districtList, pageable).getContent();
        }
        return regionRepository.findAll(pageable).getContent();
    }

    public Region getById(int id){
        return regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Регион не найден"));
    }

    public void create(Region region) {
        regionRepository.save(region);

        List<FeatureDefinition> features = featureDefinitionRepository.findAll();
        for (FeatureDefinition feature : features) {
            RegionFeatureValue value = new RegionFeatureValue();
            value.setRegion(region);
            value.setFeatureDefinition(feature);
            value.setValue("—");
            regionFeatureValueRepository.save(value);
        }
    }

    public void update(int id, Region region){
        region.setId(id);
        regionRepository.save(region);
    }

    public void deleteById(int id) {
        regionRepository.deleteById(id);
    }

    public Region getLast(){
        return regionRepository.findTopByOrderByIdDesc();
    }

    public void rename(int id, String name) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Регион не найден"));
        region.setName(name);
        regionRepository.save(region);
    }
}
