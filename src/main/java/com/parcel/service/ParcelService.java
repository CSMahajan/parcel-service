package com.parcel.service;

import com.parcel.dto.CostDetails;
import com.parcel.dto.ParcelDetails;
import com.parcel.entity.Parcel;
import com.parcel.repository.ParcelRepository;
import com.parcel.strategy.CostCalculatorStrategy;
import com.parcel.wrapper.ParcelCostDetailsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcelService {

    @Autowired
    private CostCalculatorService costCalculatorService;

    @Autowired
    private ParcelRepository parcelRepository;

    public ParcelService(CostCalculatorService costCalculatorService) {
        this.costCalculatorService = costCalculatorService;
    }

    public CostDetails fetchParcelCost(ParcelDetails parcelDetails) {
        CostCalculatorStrategy costCalculatorStrategy = costCalculatorService.calculateCostDetailsForParcel(parcelDetails);
        ParcelCostDetailsWrapper parcelWrapper = costCalculatorStrategy.calculateCost(parcelDetails);
        Parcel parcel = parcelWrapper.getParcel();
        parcelRepository.save(parcel);
        return parcelWrapper.getCostDetails();
    }

    public List<Parcel> fetchAllParcels() {
        return parcelRepository.findAll();
    }

    public ResponseEntity<Parcel> fetchParcelById(long parcelId) {
        return parcelRepository.findById(parcelId).map(person -> new ResponseEntity<>(person, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}