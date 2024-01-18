package com.parcel.constants;

public enum ParcelType {

    HEAVY_PARCEL(1,"heavy parcel"),
    SMALL_PARCEL(2,"small parcel"),
    MEDIUM_PARCEL(3,"medium parcel"),
    LARGE_PARCEL(4,"large parcel");


    private int priority;
    private String parcelTypeName;

    ParcelType(int priority, String parcelTypeName) {
        this.priority = priority;
        this.parcelTypeName = parcelTypeName;
    }

    public int getPriority() {
        return priority;
    }

    public String getParcelTypeName() {
        return parcelTypeName;
    }
}
