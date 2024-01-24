package com.parcel.service;

import com.parcel.constants.ParcelType;
import com.parcel.dto.CostDetails;
import com.parcel.dto.ParcelDetails;
import com.parcel.entity.Parcel;
import com.parcel.exception.ParcelException;
import com.parcel.repository.ParcelRepository;
import com.parcel.strategy.CostCalculatorStrategy;
import com.parcel.wrapper.ParcelCostDetailsWrapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {ParcelServiceTest.class})
public class ParcelServiceTest {

    @Mock
    private ParcelRepository parcelRepository;

    @Mock
    private CostCalculatorService costCalculatorService;

    @InjectMocks
    private ParcelService parcelService;

    @Test
    void test_findAllParcels() {
        // Given
        List<Parcel> parcelList = new ArrayList<>();
        Parcel parcel1 = new Parcel(1, 25.0, 13.0, 22.0, 211.0, 500.0, ParcelType.HEAVY_PARCEL);
        Parcel parcel2 = new Parcel(2, 2.0, 1.0, 2.0, 41.0, 600.0, ParcelType.LARGE_PARCEL);
        Parcel parcel3 = new Parcel(3, 12.0, 3.0, 20.0, 21.0, 800.0, ParcelType.MEDIUM_PARCEL);
        Parcel parcel4 = new Parcel(4, 33.0, 11.0, 56.0, 11.0, 900.0, ParcelType.SMALL_PARCEL);
        parcelList.add(parcel1);
        parcelList.add(parcel2);
        parcelList.add(parcel3);
        parcelList.add(parcel4);
        Mockito.when(parcelRepository.findAll()).thenReturn(parcelList);
        assertEquals(4, parcelService.fetchAllParcels().size());
        verify(parcelRepository, times(1)).findAll();
    }

    @Test
    void test_findParcelById() throws ParcelException {
        long parcelId = 3;
        Parcel parcel = new Parcel(3, 12.0, 3.0, 20.0, 21.0, 800.0, ParcelType.MEDIUM_PARCEL);
        Mockito.when(parcelRepository.findById(parcelId)).thenReturn(Optional.of(parcel));
        assertEquals(parcelId, parcelService.fetchParcelById(parcelId).getParcelId());
        assertEquals(12.0, parcelService.fetchParcelById(parcelId).getWeight());
    }

    @Test
    void test_updateParcelById() throws ParcelException {
        long parcelId = 1L;
        Parcel existingParcel = new Parcel(); // Assuming you have a default constructor
        Parcel updatedParcel = new Parcel();
        updatedParcel.setWeight(2.0);
        updatedParcel.setLength(10.0);
        updatedParcel.setWidth(5.0);
        updatedParcel.setHeight(3.0);
        when(parcelRepository.findById(anyLong())).thenReturn(Optional.of(existingParcel));
        when(parcelRepository.save(any())).thenReturn(updatedParcel);
        // Act
        ResponseEntity<Parcel> responseEntity = parcelService.updateParcelDetails(parcelId, updatedParcel);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedParcel, responseEntity.getBody());
        verify(parcelRepository, times(1)).findById(parcelId);
        verify(parcelRepository, times(1)).save(existingParcel);
    }

    @Test
    void testUpdateParcelDetails_NotFound() {
        // Arrange
        long nonExistentParcelId = 999L;
        Parcel updatedParcel = new Parcel();

        when(parcelRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Parcel> responseEntity = parcelService.updateParcelDetails(nonExistentParcelId, updatedParcel);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
        verify(parcelRepository, times(1)).findById(nonExistentParcelId);
        verify(parcelRepository, never()).save(any());
    }

    @Test
    void testProcessParcel_Success() throws ParcelException {
        // Arrange
        ParcelDetails parcelDetails = new ParcelDetails();
        CostCalculatorStrategy mockCostCalculatorStrategy = mock(CostCalculatorStrategy.class);
        ParcelCostDetailsWrapper mockParcelWrapper = mock(ParcelCostDetailsWrapper.class);
        CostDetails mockCostDetails = CostDetails.builder().parcelId(1L).cost(10.0).parcelTypeName("TestParcel").priority(1).build();

        when(costCalculatorService.validateAndCalculateCostDetailsOfParcel(parcelDetails)).thenReturn(mockCostCalculatorStrategy);
        when(mockCostCalculatorStrategy.calculateCost(parcelDetails)).thenReturn(mockParcelWrapper);
        when(mockParcelWrapper.getParcel()).thenReturn(new Parcel()); // Mocking a Parcel object
        when(mockParcelWrapper.getCostDetails()).thenReturn(mockCostDetails);

        // Act
        ResponseEntity<CostDetails> responseEntity = parcelService.processParcel(parcelDetails);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockCostDetails, responseEntity.getBody());

        // Verify that the parcelRepository.save was called with the correct argument
        verify(parcelRepository, times(1)).save(any(Parcel.class));
    }

   /* @Test
    void testProcessParcel_Success1() throws ParcelException {
        // Arrange
        ParcelDetails parcelDetails = new ParcelDetails(); // Set up your ParcelDetails object
        CostCalculatorStrategy costCalculatorStrategy = mock(CostCalculatorStrategy.class);
        ParcelCostDetailsWrapper parcelWrapper = new ParcelCostDetailsWrapper(); // Set up your ParcelCostDetailsWrapper object
        parcelWrapper.setCostDetails(new CostDetails()); // Set up your CostDetails object
        parcelWrapper.setParcel(new Parcel()); // Set up your Parcel object

        // Mock the behavior of costCalculatorService
        when(costCalculatorService.validateAndCalculateCostDetailsOfParcel(any())).thenReturn(costCalculatorStrategy);
        when(costCalculatorStrategy.calculateCost(any())).thenReturn(parcelWrapper);

        // Act
        ResponseEntity<CostDetails> response = parcelService.processParcel(parcelDetails);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Add more assertions based on your specific logic and expectations
    }*/

    @Test
    void testProcessParcel_NullCostCalculatorStrategy() throws ParcelException {
        // Arrange
        ParcelDetails parcelDetails = new ParcelDetails(); // Set up your ParcelDetails object

        // Mock the behavior of costCalculatorService to return null
        when(costCalculatorService.validateAndCalculateCostDetailsOfParcel(any())).thenReturn(null);

        // Act and Assert
        ParcelException exception = assertThrows(ParcelException.class,
                () -> parcelService.processParcel(parcelDetails));
        assertEquals("Cost calculator strategy is null", exception.getMessage());
    }
}
