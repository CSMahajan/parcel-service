package com.parcel.controller;

import com.parcel.dto.CostDetails;
import com.parcel.dto.ParcelDetails;
import com.parcel.exception.ParcelException;
import com.parcel.exception.ParcelExceptionHandler;
import com.parcel.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/parcel")
public class ParcelServiceController {

    @Autowired
    private ParcelExceptionHandler parcelExceptionHandler;

    @Autowired
    private ParcelService parcelService;

    @PostMapping("/cost")
    public ResponseEntity<Object> calculateCost(@RequestBody ParcelDetails parcelDetails) throws ParcelException {
        if (parcelDetails.getWeight() > 50) {
            String errorMessage = "Requested parcel is of " + parcelDetails.getWeight() + " kg. Parcel with weight above 50 kg can not be delivered";
            throw new ParcelException(errorMessage);
        }
        CostDetails costDetails = parcelService.fetchParcelCost(parcelDetails);
        return new ResponseEntity<>(costDetails, HttpStatus.OK);
    }
}
