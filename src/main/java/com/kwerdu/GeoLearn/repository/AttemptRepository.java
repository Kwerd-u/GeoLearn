package com.kwerdu.GeoLearn.repository;

import com.kwerdu.GeoLearn.model.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Integer> {
}
