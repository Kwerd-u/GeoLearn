package com.kwerdu.GeoLearn.dto;

import java.util.List;

public record QuizQuestionDto(
        int regionId,
        String regionName,
        int featureId,
        String featureName,
        String questionType,  // "MAP_TO_FEATURE" или "FEATURE_TO_MAP"
        String correctAnswer,
        List<String> options   // 4 варианта включая правильный
) {}