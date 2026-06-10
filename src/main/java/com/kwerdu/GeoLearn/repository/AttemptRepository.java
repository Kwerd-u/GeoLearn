package com.kwerdu.GeoLearn.repository;

import com.kwerdu.GeoLearn.model.Attempt;
import com.kwerdu.GeoLearn.model.Region;
import com.kwerdu.GeoLearn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Integer> {
    List<Attempt> findByUser(User user);
    List<Attempt> findByUserAndRegion(User user, Region region);
    List<Attempt> findTop10ByUserAndRegionOrderByCreatedAtDesc(User user, Region region);
    List<Attempt> findByUserAndRegion_Id(User user, int regionId);
}
