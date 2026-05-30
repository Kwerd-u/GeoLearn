package com.kwerdu.GeoLearn.controller;

import com.kwerdu.GeoLearn.model.Region;
import com.kwerdu.GeoLearn.service.RegionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public List<Region> getAll() {
        return regionService.getAll();
    }

    @GetMapping("/{id}")
    public Region getById(@PathVariable int id) {
        return regionService.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public void create(@RequestBody Region region) {
        regionService.create(region);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Region region) {
        regionService.update(id, region);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        regionService.deleteById(id);
    }
}
