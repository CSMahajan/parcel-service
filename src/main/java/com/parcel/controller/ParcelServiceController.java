package com.parcel.controller;

import com.parcel.constants.ParcelConstraints;
import com.parcel.dto.CostDetails;
import com.parcel.dto.ParcelDetails;
import com.parcel.entity.Parcel;
import com.parcel.exception.ParcelException;
import com.parcel.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/parcels")
public class ParcelServiceController {

    @Autowired
    private ParcelService parcelService;

    @PostMapping("/create")
    public ResponseEntity<Object> calculateCost(@RequestBody ParcelDetails parcelDetails) throws ParcelException {
        if (parcelDetails.getWeight() > ParcelConstraints.MAX_PARCEL_WEIGHT_LIMIT) {
            String errorMessage = "Requested parcel is of " + parcelDetails.getWeight() + " kg. Parcel with weight above " + ParcelConstraints.MAX_PARCEL_WEIGHT_LIMIT +" kg can not be delivered";
            throw new ParcelException(errorMessage);
        }
        CostDetails costDetails = parcelService.fetchParcelCost(parcelDetails);
        return new ResponseEntity<>(costDetails, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Parcel> getAllEmployees() {
        return parcelService.fetchAllParcels();
    }

    @GetMapping("/parcel/{parcelId}")
    public ResponseEntity<Parcel> getAllEmployees(@PathVariable long parcelId) {
        return parcelService.fetchParcelById(parcelId);
    }
}