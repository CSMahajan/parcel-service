package com.parcel.stategy;

import com.parcel.constants.ParcelConstraints;
import com.parcel.constants.ParcelType;
import com.parcel.dto.ParcelDetails;
import com.parcel.entity.Parcel;
import com.parcel.strategy.HeavyParcelCostCalculatorStrategy;
import com.parcel.wrapper.ParcelCostDetailsWrapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HeavyParcelCostCalculatorStrategyTest {

    @InjectMocks
    private HeavyParcelCostCalculatorStrategy heavyParcelCostCalculatorStrategy;

    @Mock
    private ParcelDetails mockParcelDetails;

    @Mock
    private ParcelCostDetailsWrapper mockParcelCostDetailsWrapper;

    @Test
    void testCalculateCost() {
        double weight = 20.0;
        double expectedCost = ParcelConstraints.HEAVY_PARCEL_COST_MULTIPLIER * weight;
        when(mockParcelDetails.getWeight()).thenReturn(weight);
        when(mockParcelDetails.getHeight()).thenReturn(2.0);
        when(mockParcelDetails.getLength()).thenReturn(3.0);
        when(mockParcelDetails.getWidth()).thenReturn(1.0);
        Parcel expectedParcel = new Parcel();
        expectedParcel.setParcelType(ParcelType.HEAVY_PARCEL);
        lenient().when(mockParcelCostDetailsWrapper.getParcelDetails()).thenReturn(mockParcelDetails);
        lenient().when(mockParcelCostDetailsWrapper.getParcel()).thenReturn(expectedParcel);
        ParcelCostDetailsWrapper result = heavyParcelCostCalculatorStrategy.calculateCost(mockParcelDetails);
        assertEquals(expectedCost, result.getCostDetails().getCost());
        assertEquals(ParcelType.HEAVY_PARCEL, result.getParcel().getParcelType());
    }
}