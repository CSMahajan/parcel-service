package com.parcel.strategy;

import com.parcel.constants.ParcelType;
import com.parcel.dto.CostDetails;
import com.parcel.dto.ParcelDetails;
import com.parcel.entity.Parcel;
import com.parcel.utils.ParcelMapperUtils;
import com.parcel.wrapper.ParcelCostDetailsWrapper;
import org.springframework.beans.factory.annotation.Autowired;

public class LargeParcelCostCalculatorStrategy implements VolumeBasedCostCalculatorStrategy{

    @Autowired
    ParcelCostDetailsWrapper parcelCostDetailsWrapper;

    private double volume;

    public LargeParcelCostCalculatorStrategy(double volume) {
        this.volume = volume;
    }


    @Override
    public ParcelCostDetailsWrapper calculateCost(ParcelDetails parcelDetails) {
        //double volume = parcelDetails.getHeight() * parcelDetails.getLength() * parcelDetails.getWidth();
        double cost = 0.05 * volume;
        Parcel parcel = ParcelMapperUtils.mapParcelDetailsToEntity(parcelDetails, cost, ParcelType.LARGE_PARCEL);
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