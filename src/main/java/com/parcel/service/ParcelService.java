package com.parcel.service;

import com.parcel.dto.CostDetails;
import com.parcel.dto.ParcelDetails;
import org.springframework.stereotype.Service;

@Service
public class ParcelService {


    public CostDetails calculateParcelCost(ParcelDetails parcelDetails) {
        CostDetails costDetails = new CostDetails();
        double cost = 0;
        if (parcelDetails.getWeight() > 50) {
            //error throw to reject the parcel
        } else if (parcelDetails.getWeight() > 10) {
            cost = 20 * parcelDetails.getWeight();
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
        costDetails.setCost(cost);
        return costDetails;
    }
}
