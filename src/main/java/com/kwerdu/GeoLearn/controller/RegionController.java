package com.kwerdu.GeoLearn.controller;

import com.kwerdu.GeoLearn.model.Region;
import com.kwerdu.GeoLearn.service.RegionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    public List<Region> getAll(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String districts,
            @RequestParam(defaultValue = "name") String sortBy) {
        return regionService.getAll(page, size, districts, sortBy);
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

    @GetMapping("/last")
    public Region getLast() {
        return regionService.getLast();
    }

    @PatchMapping("/{id}/name")
    public void rename(@PathVariable int id, @RequestBody String name, Authentication auth) {
        if (auth.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            throw new RuntimeException("Нет прав");
        }
        regionService.rename(id, name);
    }

}
