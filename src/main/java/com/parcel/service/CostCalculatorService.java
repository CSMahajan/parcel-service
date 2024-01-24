package com.parcel.service;

import com.parcel.constants.ParcelConstraints;
import com.parcel.dto.ParcelDetails;
import com.parcel.strategy.*;
import org.springframework.stereotype.Service;

@Service
public class CostCalculatorService {

    public CostCalculatorStrategy calculateCostDetailsForParcel(ParcelDetails parcelDetails) {
        if (parcelDetails.getWeight() > ParcelConstraints.HEAVY_PARCEL_WEIGHT_LIMIT) {
            return new HeavyParcelCostCalculatorStrategy();
        } else {
            double volume = parcelDetails.getHeight() * parcelDetails.getLength() * parcelDetails.getWidth();
            if (volume < ParcelConstraints.SMALL_PARCEL_VOLUME_LIMIT) {
                return new SmallParcelCostCalculatorStrategy(volume);
            } else if (volume >= ParcelConstraints.SMALL_PARCEL_VOLUME_LIMIT && volume < ParcelConstraints.LARGE_PARCEL_VOLUME_LIMIT) {
                return new MediumParcelCostCalculatorStrategy(volume);
            } else {
                return new LargeParcelCostCalculatorStrategy(volume);
            }
        }
    }
}