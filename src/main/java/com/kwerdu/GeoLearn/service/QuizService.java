package com.kwerdu.GeoLearn.service;

import com.kwerdu.GeoLearn.dto.QuizAnswerDto;
import com.kwerdu.GeoLearn.dto.QuizQuestionDto;
import com.kwerdu.GeoLearn.model.Attempt;
import com.kwerdu.GeoLearn.model.FeatureDefinition;
import com.kwerdu.GeoLearn.model.Region;
import com.kwerdu.GeoLearn.model.RegionFeatureValue;
import com.kwerdu.GeoLearn.model.User;
import com.kwerdu.GeoLearn.repository.AttemptRepository;
import com.kwerdu.GeoLearn.repository.FeatureDefinitionRepository;
import com.kwerdu.GeoLearn.repository.RegionFeatureValueRepository;
import com.kwerdu.GeoLearn.repository.RegionRepository;
import com.kwerdu.GeoLearn.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private final RegionRepository regionRepository;
    private final FeatureDefinitionRepository featureDefinitionRepository;
    private final RegionFeatureValueRepository regionFeatureValueRepository;
    private final AttemptRepository attemptRepository;
    private final UserRepository userRepository;

    public QuizService(RegionRepository regionRepository,
                       FeatureDefinitionRepository featureDefinitionRepository,
                       RegionFeatureValueRepository regionFeatureValueRepository,
                       AttemptRepository attemptRepository,
                       UserRepository userRepository) {
        this.regionRepository = regionRepository;
        this.featureDefinitionRepository = featureDefinitionRepository;
        this.regionFeatureValueRepository = regionFeatureValueRepository;
        this.attemptRepository = attemptRepository;
        this.userRepository = userRepository;
    }

    public QuizQuestionDto getNextQuestion(String nick) {
        List<Region> regions = regionRepository.findAll();
        List<FeatureDefinition> features = featureDefinitionRepository.findAll();

        if (regions.isEmpty() || features.isEmpty()) {
            throw new RuntimeException("Недостаточно данных для викторины");
        }

        Random random = new Random();

        // Выбираем рандомный регион и фичу
        Region region = regions.get(random.nextInt(regions.size()));
        FeatureDefinition feature = features.get(random.nextInt(features.size()));

        // Получаем правильный ответ
        RegionFeatureValue correctValue = regionFeatureValueRepository
                .findByRegion_IdAndFeatureDefinition_Id(region.getId(), feature.getId());

        if (correctValue == null || correctValue.getValue().isEmpty() || correctValue.getValue().equals("—")) {
            return getNextQuestion(nick); // рекурсия если нет значения
        }

        boolean isMapToFeature = random.nextBoolean();
        String questionType = isMapToFeature ? "MAP_TO_FEATURE" : "FEATURE_TO_MAP";

        List<String> options;

        if (isMapToFeature) {
            // Варианты — значения фичи из разных регионов
            List<RegionFeatureValue> allValues = regionFeatureValueRepository
                    .findByFeatureDefinition_Id(feature.getId())
                    .stream()
                    .filter(v -> !v.getValue().isEmpty() && !v.getValue().equals("—"))
                    .filter(v -> v.getRegion().getId() != region.getId())
                    .collect(Collectors.toList());

            Collections.shuffle(allValues);
            options = allValues.stream()
                    .limit(3)
                    .map(RegionFeatureValue::getValue)
                    .collect(Collectors.toList());
            options.add(correctValue.getValue());
        } else {
            // Варианты — названия регионов
            List<Region> otherRegions = regions.stream()
                    .filter(r -> r.getId() != region.getId())
                    .collect(Collectors.toList());

            Collections.shuffle(otherRegions);
            options = otherRegions.stream()
                    .limit(3)
                    .map(Region::getName)
                    .collect(Collectors.toList());
            options.add(region.getName());
        }

        Collections.shuffle(options);

        return new QuizQuestionDto(
                region.getId(),
                region.getName(),
                feature.getId(),
                feature.getDisplayName(),
                questionType,
                isMapToFeature ? correctValue.getValue() : region.getName(),
                options
        );
    }

    public boolean submitAnswer(String nick, QuizAnswerDto answer) {
        User user = userRepository.findByNick(nick)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Region region = regionRepository.findById(answer.regionId())
                .orElseThrow(() -> new RuntimeException("Регион не найден"));
        FeatureDefinition feature = featureDefinitionRepository.findById(answer.featureId())
                .orElseThrow(() -> new RuntimeException("Фича не найдена"));

        RegionFeatureValue correctValue = regionFeatureValueRepository
                .findByRegion_IdAndFeatureDefinition_Id(region.getId(), feature.getId());

        boolean isCorrect = answer.answer().equals(correctValue.getValue()) ||
                answer.answer().equals(region.getName());

        Attempt attempt = new Attempt();
        attempt.setUser(user);
        attempt.setRegion(region);
        attempt.setFeatureDefinition(feature);
        attempt.setCorrect(isCorrect);
        attempt.setCreatedAt(LocalDateTime.now());
        attemptRepository.save(attempt);

        return isCorrect;
    }
}