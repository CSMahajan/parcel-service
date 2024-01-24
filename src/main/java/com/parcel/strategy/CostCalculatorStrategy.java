package com.parcel.strategy;

import com.parcel.dto.ParcelDetails;
import com.parcel.wrapper.ParcelCostDetailsWrapper;

public interface CostCalculatorStrategy {
    ParcelCostDetailsWrapper calculateCost(ParcelDetails parcelDetails);
}