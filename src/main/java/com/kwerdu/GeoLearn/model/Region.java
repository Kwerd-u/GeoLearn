package com.kwerdu.GeoLearn.model;

import com.kwerdu.GeoLearn.model.enums.FederalDistrict;
import jakarta.persistence.*;

@Entity
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FederalDistrict federalDistrict;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FederalDistrict getFederalDistrict() {
        return federalDistrict;
    }

    public void setFederalDistrict(FederalDistrict federalDistrict) {
        this.federalDistrict = federalDistrict;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
