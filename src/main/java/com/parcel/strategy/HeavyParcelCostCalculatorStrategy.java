package com.parcel.strategy;

import com.parcel.constants.ParcelConstraints;
import com.parcel.constants.ParcelType;
import com.parcel.dto.CostDetails;
import com.parcel.dto.ParcelDetails;
import com.parcel.entity.Parcel;
import com.parcel.utils.ParcelMapperUtils;
import com.parcel.wrapper.ParcelCostDetailsWrapper;
import org.springframework.beans.factory.annotation.Autowired;

public class HeavyParcelCostCalculatorStrategy implements WeightBasedCostCalculatorStrategy{

    @Autowired
    ParcelCostDetailsWrapper parcelCostDetailsWrapper;

    @Override
    public ParcelCostDetailsWrapper calculateCost(ParcelDetails parcelDetails) {
        double cost = ParcelConstraints.HEAVY_PARCEL_COST_MULTIPLIER * parcelDetails.getWeight();
        Parcel parcel = ParcelMapperUtils.mapParcelDetailsToEntity(parcelDetails, cost, ParcelType.HEAVY_PARCEL);
        CostDetails costDetails = CostDetails.builder().cost(cost).
                parcelTypeName(parcel.getParcelType().getParcelTypeName())
                .priority(parcel.getParcelType().getPriority()).build();
        parcelCostDetailsWrapper = new ParcelCostDetailsWrapper();
        parcelCostDetailsWrapper.setCostDetails(costDetails);
        parcelCostDetailsWrapper.setParcelDetails(parcelDetails);
        parcelCostDetailsWrapper.setParcel(parcel);
        return parcelCostDetailsWrapper;
    }
}