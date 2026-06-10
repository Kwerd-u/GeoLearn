package com.kwerdu.GeoLearn.dto;

public record RegionStatDto(
        int regionId,
        String regionName,
        String name, Double accuracy
) {}