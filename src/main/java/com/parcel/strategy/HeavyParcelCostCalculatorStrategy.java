package com.parcel.strategy;

import com.parcel.dto.CostDetails;
import com.parcel.dto.ParcelDetails;

public class HeavyParcelCostCalculatorStrategy implements CostCalculatorStrategy{
    @Override
    public CostDetails calculateCost(ParcelDetails parcelDetails) {
        double cost = 20 * parcelDetails.getWeight();

    }
}
