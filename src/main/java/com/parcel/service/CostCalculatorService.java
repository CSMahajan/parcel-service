package com.parcel.service;

import com.parcel.dto.ParcelDetails;
import com.parcel.strategy.*;
import org.springframework.stereotype.Service;

@Service
public class CostCalculatorService {

    public CostCalculatorStrategy calculateCostDetailsForParcel(ParcelDetails parcelDetails) {
        //double cost = 0;

        if (parcelDetails.getWeight() > 10) {
            //cost = 20 * parcelDetails.getWeight();
            return new HeavyParcelCostCalculatorStrategy();
        } else {
            double volume = parcelDetails.getHeight() * parcelDetails.getLength() * parcelDetails.getWidth();
            if (volume < 1500) {
                //cost = 0.03 * volume;
                return new SmallParcelCostCalculatorStrategy(volume);
            } else if (volume >= 1500 && volume < 2500) {
                //cost = 0.04 * volume;
                return new MediumParcelCostCalculatorStrategy(volume);
            } else {
                //cost = 0.05 * volume;
                return new LargeParcelCostCalculatorStrategy(volume);
            }
        }
    }
}
