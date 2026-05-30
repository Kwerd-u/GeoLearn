package com.kwerdu.GeoLearn.controller;

import com.kwerdu.GeoLearn.model.FeatureDefinition;
import com.kwerdu.GeoLearn.model.RegionFeatureValue;
import com.kwerdu.GeoLearn.service.FeatureDefinitionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feature_definitions")
public class FeatureDefinitionController {
    private final FeatureDefinitionService featureDefinitionService;

    public FeatureDefinitionController(FeatureDefinitionService featureDefinitionService) {
        this.featureDefinitionService = featureDefinitionService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public void create(@RequestBody FeatureDefinition featureDefinition){
        featureDefinitionService.create(featureDefinition);
    }

    @GetMapping
    public List<FeatureDefinition> getAll(){
        return featureDefinitionService.getAll();
    }

    @GetMapping("/{id}/values")
    public List<RegionFeatureValue> getById(@PathVariable int id){
        return featureDefinitionService.getAllByFeatureDefinitionId(id);
    }
}
