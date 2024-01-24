package com.parcel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class CostDetails {
    private long parcelId;
    private double cost;
    private String parcelTypeName;
    private int priority;
}