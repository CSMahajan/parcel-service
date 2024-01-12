package com.parcel.controller;

import com.parcel.dto.CostDetails;
import com.parcel.dto.ParcelDetails;
import com.parcel.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/parcel")
public class ParcelServiceController {

    @Autowired
    private ParcelService parcelService;

    @GetMapping("/cost")
    public CostDetails calculateCost(@RequestBody ParcelDetails parcelDetails) {
        return parcelService.calculateParcelCost(parcelDetails);
    }
}
