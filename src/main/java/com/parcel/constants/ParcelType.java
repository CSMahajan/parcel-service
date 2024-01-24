package com.parcel.constants;

import lombok.Getter;

@Getter
public enum ParcelType {

    HEAVY_PARCEL(1,"heavy parcel"),
    SMALL_PARCEL(2,"small parcel"),
    MEDIUM_PARCEL(3,"medium parcel"),
    LARGE_PARCEL(4,"large parcel");

    private final int priority;
    private final String parcelTypeName;

    ParcelType(int priority, String parcelTypeName) {
        this.priority = priority;
        this.parcelTypeName = parcelTypeName;
    }
}
