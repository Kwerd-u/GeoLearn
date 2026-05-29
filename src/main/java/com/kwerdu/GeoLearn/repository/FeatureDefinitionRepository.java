package com.kwerdu.GeoLearn.repository;

import com.kwerdu.GeoLearn.model.FeatureDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureDefinitionRepository extends JpaRepository<FeatureDefinition, Integer> {
}
