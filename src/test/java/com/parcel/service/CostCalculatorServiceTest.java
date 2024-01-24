package com.parcel.service;

import com.parcel.constants.ParcelConstraints;
import com.parcel.dto.ParcelDetails;
import com.parcel.exception.ParcelException;
import com.parcel.strategy.CostCalculatorStrategy;
import com.parcel.strategy.HeavyParcelCostCalculatorStrategy;
import com.parcel.strategy.SmallParcelCostCalculatorStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CostCalculatorServiceTest {

    @InjectMocks
    private CostCalculatorService costCalculatorService;

    @Mock
    private ParcelDetails mockParcelDetails;

    @Test
    void test_validateAndCalculateCostDetailsOfParcel_HeavyParcel() throws ParcelException {
        when(mockParcelDetails.getWeight()).thenReturn((double) (ParcelConstraints.HEAVY_PARCEL_WEIGHT_LIMIT + 1));
        CostCalculatorStrategy strategy = costCalculatorService.validateAndCalculateCostDetailsOfParcel(mockParcelDetails);
        assertTrue(strategy instanceof HeavyParcelCostCalculatorStrategy);
    }

    @Test
    void test_validateAndCalculateCostDetailsOfParcel_SmallParcel() throws ParcelException {
        when(mockParcelDetails.getWeight()).thenReturn(5.0);
        when(mockParcelDetails.getHeight()).thenReturn(1.0);
        when(mockParcelDetails.getLength()).thenReturn(2.0);
        when(mockParcelDetails.getWidth()).thenReturn(2.0);
        CostCalculatorStrategy strategy = costCalculatorService.validateAndCalculateCostDetailsOfParcel(mockParcelDetails);
        assertTrue(strategy instanceof SmallParcelCostCalculatorStrategy);
    }

    @Test
    void test_validateAndCalculateCostDetailsOfParcel_OverWeightParcel() {
        when(mockParcelDetails.getWeight()).thenReturn(51.0);
        ParcelException exception = assertThrows(ParcelException.class,
                () -> costCalculatorService.validateAndCalculateCostDetailsOfParcel(mockParcelDetails));

        assertEquals("Requested parcel is of " + 51.0 +
                        " kg. Parcel with weight above " + ParcelConstraints.MAX_PARCEL_WEIGHT_LIMIT + " kg can not be delivered",
                exception.getMessage());
    }
}