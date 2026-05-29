package com.kwerdu.GeoLearn.service;

import com.kwerdu.GeoLearn.model.Region;
import com.kwerdu.GeoLearn.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    private final RegionRepository regionRepository;

    public RegionService (RegionRepository regionRepository){
        this.regionRepository = regionRepository;
    }

    public List<Region> getAll(){
        return regionRepository.findAll();
    }

    public Region getById(int id){
        return regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Регион не найден"));
    }

    public void create(Region region){
        regionRepository.save(region);
    }

    public void update(int id, Region region){
        region.setId(id);
        regionRepository.save(region);
    }
}
