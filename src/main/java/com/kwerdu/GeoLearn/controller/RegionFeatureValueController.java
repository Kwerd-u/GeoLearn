package com.kwerdu.GeoLearn.controller;

import com.kwerdu.GeoLearn.model.RegionFeatureValue;
import com.kwerdu.GeoLearn.service.RegionFeatureValueService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RegionFeatureValueController {
    private final RegionFeatureValueService regionFeatureValueService;

    public RegionFeatureValueController(RegionFeatureValueService regionFeatureValueService) {
        this.regionFeatureValueService = regionFeatureValueService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/feature_definitions/{id}/values/{regionId}")
    public void setRegionFeatureValue(@PathVariable int id, @PathVariable int regionId, @RequestBody Map<String, String> body) {
        regionFeatureValueService.update(regionId, id, body.get("value"));
    }

    @GetMapping("/api/regions/{regionId}/features")
    public List<RegionFeatureValue> getByRegion(@PathVariable int regionId) {
        return regionFeatureValueService.getByRegionId(regionId);
    }


}
