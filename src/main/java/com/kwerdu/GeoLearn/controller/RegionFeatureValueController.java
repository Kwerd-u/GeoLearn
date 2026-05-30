package com.kwerdu.GeoLearn.controller;

import com.kwerdu.GeoLearn.service.RegionFeatureValueService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegionFeatureValueController {
    private final RegionFeatureValueService regionFeatureValueService;

    public RegionFeatureValueController(RegionFeatureValueService regionFeatureValueService) {
        this.regionFeatureValueService = regionFeatureValueService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/feature_definitions/{id}/values/{regionId}")
    public void setRegionFeatureValue(@PathVariable int id, @PathVariable int regionId, @RequestBody String value){
        regionFeatureValueService.update(regionId, id, value);
    }
}
