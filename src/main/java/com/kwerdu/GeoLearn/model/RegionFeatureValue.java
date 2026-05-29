package com.kwerdu.GeoLearn.model;

import jakarta.persistence.*;

@Entity
@Table(name = "region_feature_types")
public class RegionFeatureValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @ManyToOne
    @JoinColumn(name = "feature_definition_id", nullable = false)
    private FeatureDefinition featureDefinition;

    @Column(nullable = false)
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public FeatureDefinition getFeatureDefinition() {
        return featureDefinition;
    }

    public void setFeatureDefinition(FeatureDefinition featureDefinition) {
        this.featureDefinition = featureDefinition;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
