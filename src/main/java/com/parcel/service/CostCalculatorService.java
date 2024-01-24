package com.parcel.service;

import com.parcel.constants.ParcelConstraints;
import com.parcel.dto.ParcelDetails;
import com.parcel.exception.ParcelException;
import com.parcel.strategy.*;
import org.springframework.stereotype.Service;

@Service
public class CostCalculatorService {

    public CostCalculatorStrategy validateAndCalculateCostDetailsOfParcel(ParcelDetails parcelDetails) throws ParcelException {
        validateParcel(parcelDetails);
        double weight = parcelDetails.getWeight();
        if (isHeavyParcel(weight)) {
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

    private void validateParcel(ParcelDetails parcelDetails) throws ParcelException {
        if (parcelDetails == null) {
            processNullParcel();
        }
        double weight = parcelDetails.getWeight();
        if (isOverWeightParcel(weight)) {
            processOverWeightParcel(weight);
        }
    }

    private void processNullParcel() throws ParcelException {
        String errorMessage = "Requested parcel is null";
        throw new ParcelException(errorMessage);
    }

    private void processOverWeightParcel(double weight) throws ParcelException {
        String errorMessage = "Requested parcel is of " + weight + " kg. Parcel with weight above " + ParcelConstraints.MAX_PARCEL_WEIGHT_LIMIT + " kg can not be delivered";
        throw new ParcelException(errorMessage);
    }

    protected boolean isOverWeightParcel(double weight) {
        return weight > ParcelConstraints.MAX_PARCEL_WEIGHT_LIMIT;
    }


    private static boolean isHeavyParcel(double weight) {
        return weight > ParcelConstraints.HEAVY_PARCEL_WEIGHT_LIMIT;
    }

    private static boolean isSmallParcel(double volume) {
        return volume < ParcelConstraints.SMALL_PARCEL_VOLUME_LIMIT;
    }

    private static boolean isMediumParcel(double volume) {
        return volume >= ParcelConstraints.SMALL_PARCEL_VOLUME_LIMIT && volume < ParcelConstraints.LARGE_PARCEL_VOLUME_LIMIT;
    }
}