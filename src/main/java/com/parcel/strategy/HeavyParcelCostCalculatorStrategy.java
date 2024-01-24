package com.parcel.strategy;

import com.parcel.constants.ParcelConstraints;
import com.parcel.constants.ParcelType;
import com.parcel.dto.CostDetails;
import com.parcel.dto.ParcelDetails;
import com.parcel.entity.Parcel;
import com.parcel.utils.ParcelMapperUtils;
import com.parcel.wrapper.ParcelCostDetailsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HeavyParcelCostCalculatorStrategy implements WeightBasedCostCalculatorStrategy {

    @Autowired
    ParcelCostDetailsWrapper parcelCostDetailsWrapper;

    @Override
    public ParcelCostDetailsWrapper calculateCost(ParcelDetails parcelDetails) {
        double weight = parcelDetails.getWeight();
        double cost = ParcelConstraints.HEAVY_PARCEL_COST_MULTIPLIER * weight;
        Parcel parcel = ParcelMapperUtils.mapParcelDetailsToEntity(parcelDetails, cost, weight, ParcelType.HEAVY_PARCEL);
        CostDetails costDetails = CostDetails.builder()
                .cost(cost)
                .parcelTypeName(parcel.getParcelType().getParcelTypeName())
                .priority(parcel.getParcelType().getPriority())
                .parcelId(parcel.getParcelId())
                .build();
        parcelCostDetailsWrapper = new ParcelCostDetailsWrapper();
        parcelCostDetailsWrapper.setCostDetails(costDetails);
        parcelCostDetailsWrapper.setParcelDetails(parcelDetails);
        parcelCostDetailsWrapper.setParcel(parcel);
        return parcelCostDetailsWrapper;
    }
}