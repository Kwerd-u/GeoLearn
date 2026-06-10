package com.kwerdu.GeoLearn.controller;

import com.kwerdu.GeoLearn.dto.FeatureStatDto;
import com.kwerdu.GeoLearn.dto.RegionStatDto;
import com.kwerdu.GeoLearn.service.StatsService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping
    public List<RegionStatDto> getRegionStats(Authentication auth) {
        return statsService.getRegionStats(auth.getName());
    }

    @GetMapping("/{regionId}")
    public List<FeatureStatDto> getFeatureStats(
            @PathVariable int regionId,
            Authentication auth) {
        return statsService.getFeatureStats(auth.getName(), regionId);
    }
}