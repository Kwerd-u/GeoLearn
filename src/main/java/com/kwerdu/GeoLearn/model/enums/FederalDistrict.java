package com.kwerdu.GeoLearn.model.enums;

public enum FederalDistrict {
    CFD("Центральный федеральный округ"),
    NWFD("Северо-Западный федеральный округ"),
    SFD("Южный федеральный округ"),
    NCFD("Северо-Кавказский федеральный округ"),
    VFD("Приволжский федеральный округ"),
    UFD("Уральский федеральный округ"),
    SibFD("Сибирский федеральный округ"),
    FEFD("Дальневосточный федеральный округ");

    private final String displayName;

    FederalDistrict(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
