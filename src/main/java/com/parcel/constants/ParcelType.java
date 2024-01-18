package com.parcel.constants;

public enum ParcelType {

    HEAVY_PARCEL(1,"heavy parcel"),
    SMALL_PARCEL(2,"small parcel"),
    MEDIUM_PARCEL(3,"medium parcel"),
    LARGE_PARCEL(4,"large parcel");


    private int priority;
    private String parcelType;

    ParcelType(int priority, String parcelType) {
        this.priority = priority;
        this.parcelType = parcelType;
    }

    public int getPriority() {
        return priority;
    }

    public String getParcelType() {
        return parcelType;
    }
}
