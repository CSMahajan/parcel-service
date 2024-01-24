package com.parcel.strategy;

import com.parcel.constants.ParcelConstraints;
import com.parcel.constants.ParcelType;
import com.parcel.dto.CostDetails;
import com.parcel.dto.ParcelDetails;
import com.parcel.entity.Parcel;
import com.parcel.wrapper.ParcelCostDetailsWrapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        // Arrange
        double weight = 20.0;
        double expectedCost = ParcelConstraints.HEAVY_PARCEL_COST_MULTIPLIER * weight;

        // Set up mockParcelDetails
        when(mockParcelDetails.getWeight()).thenReturn(weight);
        when(mockParcelDetails.getHeight()).thenReturn(2.0);
        when(mockParcelDetails.getLength()).thenReturn(3.0);
        when(mockParcelDetails.getWidth()).thenReturn(1.0);

        // Set up expectedParcel
        Parcel expectedParcel = new Parcel();
        expectedParcel.setHeight(2.0);
        expectedParcel.setWeight(20.0);
        expectedParcel.setWidth(1.0);
        expectedParcel.setLength(3.0);
        expectedParcel.setParcelType(ParcelType.HEAVY_PARCEL);

        // Set up mockParcelCostDetailsWrapper
        when(mockParcelCostDetailsWrapper.getParcelDetails()).thenReturn(mockParcelDetails);
        when(mockParcelCostDetailsWrapper.getParcel()).thenReturn(expectedParcel);

        // Act
        ParcelCostDetailsWrapper result = heavyParcelCostCalculatorStrategy.calculateCost(mockParcelDetails);

        // Assert
        // Verify interactions with mockParcelDetails
        verify(mockParcelDetails, times(1)).getWeight();
        verify(mockParcelDetails, times(1)).getHeight();
        verify(mockParcelDetails, times(1)).getLength();
        verify(mockParcelDetails, times(1)).getWidth();

        // Verify result
        assertEquals(expectedCost, result.getCostDetails().getCost());
        assertEquals(ParcelType.HEAVY_PARCEL, result.getParcel().getParcelType());

        // Additional verification for completeness
        assertEquals(expectedParcel, result.getParcel());
        assertEquals(expectedCost, result.getCostDetails().getCost());
        assertEquals(weight, result.getParcelDetails().getWeight());
    }
}