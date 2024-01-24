package com.parcel.service;

import com.parcel.constants.ParcelConstraints;
import com.parcel.dto.ParcelDetails;
import com.parcel.strategy.*;
import org.springframework.stereotype.Service;

@Service
public class CostCalculatorService {

    public CostCalculatorStrategy calculateCostDetailsForParcel(ParcelDetails parcelDetails) {
        if (isHeavyParcel(parcelDetails)) {
            return new HeavyParcelCostCalculatorStrategy();
        } else {
            double volume = parcelDetails.getHeight() * parcelDetails.getLength() * parcelDetails.getWidth();
            if (isSmallParcel(volume)) {
                return new SmallParcelCostCalculatorStrategy(volume);
            } else if (isMediumParcel(volume)) {
                return new MediumParcelCostCalculatorStrategy(volume);
            } else {
                return new LargeParcelCostCalculatorStrategy(volume);
            }
        }
    }

    private static boolean isHeavyParcel(ParcelDetails parcelDetails) {
        return parcelDetails.getWeight() > ParcelConstraints.HEAVY_PARCEL_WEIGHT_LIMIT;
    }

    private static boolean isSmallParcel(double volume) {
        return volume < ParcelConstraints.SMALL_PARCEL_VOLUME_LIMIT;
    }

    private static boolean isMediumParcel(double volume) {
        return volume >= ParcelConstraints.SMALL_PARCEL_VOLUME_LIMIT && volume < ParcelConstraints.LARGE_PARCEL_VOLUME_LIMIT;
    }
}