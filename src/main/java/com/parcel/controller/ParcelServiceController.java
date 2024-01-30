package com.parcel.controller;

import com.parcel.dto.CostDetails;
import com.parcel.dto.ParcelDetails;
import com.parcel.entity.Parcel;
import com.parcel.exception.ParcelException;
import com.parcel.service.ParcelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@RequestMapping("/v1/api/parcels")
@EnableSwagger2
@CrossOrigin
@Api(value = "Parcel API", description = "CRUD operations for Parcel")
public class ParcelServiceController {

    @Autowired
    private ParcelService parcelService;

    @PostMapping("/create-parcel")
    @ApiOperation(value = "Create a new parcel", response = CostDetails.class)
    public ResponseEntity<CostDetails> calculateCost(@RequestBody ParcelDetails parcelDetails) throws ParcelException {
        return parcelService.processParcel(parcelDetails);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Retrieve all parcels", response = Parcel.class)
    public List<Parcel> getAllParcels() {
        return parcelService.fetchAllParcels();
    }

    @GetMapping("/parcel/{parcelId}")
    @ApiOperation(value = "Retrieve a parcel by ID", response = Parcel.class)
    public Parcel getParcelById(@PathVariable long parcelId) throws ParcelException {
        return parcelService.fetchParcelById(parcelId);
    }

    @PutMapping("/update-parcel/{parcelId}")
    @ApiOperation(value = "Update a parcel by ID", response = Parcel.class)
    public ResponseEntity<Parcel> updateParcel(@PathVariable long parcelId, @RequestBody Parcel updatedParcel) {
        return parcelService.updateParcelDetails(parcelId, updatedParcel);
    }

    @DeleteMapping("/delete-parcel/{parcelId}")
    @ApiOperation(value = "Delete a parcel by ID")
    public ResponseEntity<Void> deleteParcel(@PathVariable long parcelId) {
        return parcelService.deleteParcel(parcelId);
    }
}