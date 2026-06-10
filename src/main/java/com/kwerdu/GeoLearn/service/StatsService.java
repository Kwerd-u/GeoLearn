package com.kwerdu.GeoLearn.service;

import com.kwerdu.GeoLearn.dto.FeatureStatDto;
import com.kwerdu.GeoLearn.dto.RegionStatDto;
import com.kwerdu.GeoLearn.model.Attempt;
import com.kwerdu.GeoLearn.model.Region;
import com.kwerdu.GeoLearn.model.User;
import com.kwerdu.GeoLearn.repository.AttemptRepository;
import com.kwerdu.GeoLearn.repository.RegionRepository;
import com.kwerdu.GeoLearn.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatsService {

    private final AttemptRepository attemptRepository;
    private final RegionRepository regionRepository;
    private final UserRepository userRepository;

    public StatsService(AttemptRepository attemptRepository,
                        RegionRepository regionRepository,
                        UserRepository userRepository) {
        this.attemptRepository = attemptRepository;
        this.regionRepository = regionRepository;
        this.userRepository = userRepository;
    }

    public List<RegionStatDto> getRegionStats(String nick) {
        User user = userRepository.findByNick(nick)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        List<Region> regions = regionRepository.findAll();
        List<Attempt> attempts = attemptRepository.findByUser(user);

        Map<Integer, List<Attempt>> byRegion = attempts.stream()
                .collect(Collectors.groupingBy(a -> a.getRegion().getId()));

        return regions.stream().map(region -> {
            List<Attempt> regionAttempts = byRegion.getOrDefault(region.getId(), List.of());
            Double accuracy = regionAttempts.isEmpty() ? null :
                    regionAttempts.stream().filter(a -> Boolean.TRUE.equals(a.getCorrect())).count() * 100.0 / regionAttempts.size();
            return new RegionStatDto(
                    region.getId(),
                    region.getName(),
                    region.getFederalDistrict().name(),
                    accuracy
            );
        }).toList();
    }

    public List<FeatureStatDto> getFeatureStats(String nick, int regionId) {
        User user = userRepository.findByNick(nick)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        List<Attempt> attempts = attemptRepository.findByUserAndRegion_Id(user, regionId);

        Map<Integer, List<Attempt>> byFeature = attempts.stream()
                .collect(Collectors.groupingBy(a -> a.getFeatureDefinition().getId()));

        return byFeature.entrySet().stream().map(entry -> {
            List<Attempt> featureAttempts = entry.getValue();
            String featureName = featureAttempts.get(0).getFeatureDefinition().getDisplayName();
            String lastValue = Boolean.TRUE.equals(featureAttempts.get(featureAttempts.size() - 1).getCorrect()) ? "✓" : "✗";
            Double accuracy = featureAttempts.stream().filter(a -> Boolean.TRUE.equals(a.getCorrect())).count() * 100.0 / featureAttempts.size();
            return new FeatureStatDto(entry.getKey(), featureName, lastValue, accuracy);
        }).toList();
    }
}