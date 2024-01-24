package com.parcel.service;
import com.parcel.constants.ParcelConstraints;
import com.parcel.dto.ParcelDetails;
import com.parcel.exception.ParcelException;
import com.parcel.strategy.CostCalculatorStrategy;
import com.parcel.strategy.HeavyParcelCostCalculatorStrategy;
import com.parcel.strategy.SmallParcelCostCalculatorStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CostCalculatorServiceTest {

    @InjectMocks
    private CostCalculatorService costCalculatorService;

    @Mock
    private ParcelDetails mockParcelDetails;

    @BeforeEach
    void setUp() {
        // Any common setup or initialization can be done here
    }

    @Test
    void testValidateAndCalculateCostDetailsOfParcel_HeavyParcel() throws ParcelException {
        // Arrange
        when(mockParcelDetails.getWeight()).thenReturn((double) (ParcelConstraints.HEAVY_PARCEL_WEIGHT_LIMIT + 1));

        // Act
        CostCalculatorStrategy strategy = costCalculatorService.validateAndCalculateCostDetailsOfParcel(mockParcelDetails);

        // Assert
        assertTrue(strategy instanceof HeavyParcelCostCalculatorStrategy);
    }

    @Test
    void testValidateAndCalculateCostDetailsOfParcel_SmallParcel() throws ParcelException {
        // Arrange
        when(mockParcelDetails.getWeight()).thenReturn(5.0);  // Below heavy limit
        when(mockParcelDetails.getHeight()).thenReturn(1.0);
        when(mockParcelDetails.getLength()).thenReturn(2.0);
        when(mockParcelDetails.getWidth()).thenReturn(2.0);

        // Act
        CostCalculatorStrategy strategy = costCalculatorService.validateAndCalculateCostDetailsOfParcel(mockParcelDetails);

        // Assert
        assertTrue(strategy instanceof SmallParcelCostCalculatorStrategy);
    }

    @Test
    void testValidateAndCalculateCostDetailsOfParcel_OverWeightParcel() {
        // Arrange
        when(mockParcelDetails.getWeight()).thenReturn(51.0);

        // Act & Assert
        ParcelException exception = assertThrows(ParcelException.class,
                () -> costCalculatorService.validateAndCalculateCostDetailsOfParcel(mockParcelDetails));

        assertEquals("Requested parcel is of " + 51.0 +
                        " kg. Parcel with weight above " + ParcelConstraints.MAX_PARCEL_WEIGHT_LIMIT + " kg can not be delivered",
                exception.getMessage());
    }

    // Additional test cases for other scenarios can be added here
}