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
@Api(value = "Parcel API", description = "CRUD operations for Parcel")
public class ParcelServiceController {

    @Autowired
    private ParcelService parcelService;

    @PostMapping("/create")
    @ApiOperation(value = "Create a new parcel", response = CostDetails.class)
    public ResponseEntity<Object> calculateCost(@RequestBody ParcelDetails parcelDetails) throws ParcelException {
        CostDetails costDetails = parcelService.processParcel(parcelDetails);
        return new ResponseEntity<>(costDetails, HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Retrieve all parcels", response = Parcel.class)
    public List<Parcel> getAllEmployees() {
        return parcelService.fetchAllParcels();
    }

    @GetMapping("/parcel/{parcelId}")
    @ApiOperation(value = "Retrieve a parcel by ID", response = Parcel.class)
    public ResponseEntity<Parcel> getAllEmployees(@PathVariable long parcelId) {
        return parcelService.fetchParcelById(parcelId);
    }
}