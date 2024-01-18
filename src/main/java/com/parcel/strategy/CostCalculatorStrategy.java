package com.parcel.strategy;

import com.parcel.dto.CostDetails;
import com.parcel.dto.ParcelDetails;

public interface CostCalculatorStrategy {

    CostDetails calculateCost(ParcelDetails parcelDetails);
}
