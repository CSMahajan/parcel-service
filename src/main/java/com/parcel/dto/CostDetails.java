package com.parcel.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CostDetails {
    private double cost;
    private String parcelTypeName;
    private int priority;
}
