package com.parcel.service;

import com.parcel.dto.CostDetails;
import com.parcel.dto.ParcelDetails;
import com.parcel.entity.Parcel;
import com.parcel.exception.ParcelException;
import com.parcel.repository.ParcelRepository;
import com.parcel.strategy.CostCalculatorStrategy;
import com.parcel.wrapper.ParcelCostDetailsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParcelService {

    @Autowired
    private CostCalculatorService costCalculatorService;

    @Autowired
    private ParcelRepository parcelRepository;

    public ParcelService(CostCalculatorService costCalculatorService) {
        this.costCalculatorService = costCalculatorService;
    }

    public ResponseEntity<CostDetails> processParcel(ParcelDetails parcelDetails) throws ParcelException {
        CostCalculatorStrategy costCalculatorStrategy = costCalculatorService.validateAndCalculateCostDetailsOfParcel(parcelDetails);
        if (costCalculatorStrategy == null) {
            String errorMessage = "Cost calculator strategy is null";
            throw new ParcelException(errorMessage);
        }
        ParcelCostDetailsWrapper parcelWrapper = costCalculatorStrategy.calculateCost(parcelDetails);
        Parcel parcel = parcelWrapper.getParcel();
        parcelRepository.save(parcel);
        return new ResponseEntity<>(parcelWrapper.getCostDetails(), HttpStatus.OK);
    }

    public List<Parcel> fetchAllParcels() {
        return parcelRepository.findAll();
    }

    public Parcel fetchParcelById(long parcelId) throws ParcelException {
        Optional<Parcel> parcelOptional = parcelRepository.findById(parcelId);
        if (parcelOptional.isPresent()) {
            return parcelOptional.get();
        } else throw new ParcelException("Parcel not found with id " + parcelId);
    }

    public ResponseEntity<Parcel> updateParcelDetails(long parcelId, Parcel updatedParcel) {
        return parcelRepository.findById(parcelId)
                .map(parcel -> {
                    parcel.setWeight(updatedParcel.getWeight());
                    parcel.setLength(updatedParcel.getLength());
                    parcel.setWidth(updatedParcel.getWidth());
                    parcel.setHeight(updatedParcel.getHeight());
                    return new ResponseEntity<>(parcelRepository.save(parcel), HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Void> deleteParcel(long parcelId) {
        if (parcelRepository.existsById(parcelId)) {
            parcelRepository.deleteById(parcelId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}