package com.kwerdu.GeoLearn.dto;

public record QuizAnswerDto(
        int regionId,
        int featureId,
        String answer
) {}