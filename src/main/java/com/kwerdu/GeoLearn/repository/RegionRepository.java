package com.kwerdu.GeoLearn.repository;

import com.kwerdu.GeoLearn.model.Region;
import com.kwerdu.GeoLearn.model.enums.FederalDistrict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    Region findTopByOrderByIdDesc();
    Page<Region> findByFederalDistrictIn(List<FederalDistrict> districts, Pageable pageable);
}