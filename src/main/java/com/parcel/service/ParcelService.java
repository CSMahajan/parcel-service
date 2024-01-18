package com.parcel.service;

import com.parcel.dto.CostDetails;
import com.parcel.dto.ParcelDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParcelService {

    @Autowired
    private CostCalculatorService costCalculatorService;

    public CostDetails fetchParcelCost(ParcelDetails parcelDetails) {

        CostDetails costDetails = costCalculatorService.calculateCostDetailsForParcel(parcelDetails);
        return costDetails;
    }
}
