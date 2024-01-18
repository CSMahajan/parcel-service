package com.parcel.service;

import com.parcel.dto.CostDetails;
import com.parcel.dto.ParcelDetails;
import com.parcel.strategy.CostCalculatorStrategy;
import com.parcel.strategy.HeavyParcelCostCalculatorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CostCalculatorService {

    @Autowired
    private CostCalculatorStrategy costCalculatorStrategy;

    public CostCalculatorStrategy calculateCostDetailsForParcel(ParcelDetails parcelDetails) {
        CostDetails costDetails = new CostDetails();
        double cost = 0;

        if (parcelDetails.getWeight() > 10) {
            cost = 20 * parcelDetails.getWeight();
            return new HeavyParcelCostCalculatorStrategy();
        } else {
            double volume = parcelDetails.getHeight() * parcelDetails.getLength() * parcelDetails.getWidth();
            if (volume < 1500) {
                cost = 0.03 * volume;
            } else if (volume >= 1500 && volume < 2500) {
                cost = 0.04 * volume;
            } else {
                cost = 0.05 * volume;
            }
        }
    }
}
