package com.kwerdu.GeoLearn.dto;

public record FeatureStatDto(
        int featureId,
        String featureName,
        String lastValue,
        Double accuracy
) {}
