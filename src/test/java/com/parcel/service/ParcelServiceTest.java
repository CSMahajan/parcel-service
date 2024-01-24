package com.parcel.service;

import com.parcel.constants.ParcelType;
import com.parcel.entity.Parcel;
import com.parcel.exception.ParcelException;
import com.parcel.repository.ParcelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {ParcelServiceTest.class})
public class ParcelServiceTest {

    @Mock
    private ParcelRepository parcelRepository;

    @InjectMocks
    private ParcelService parcelService;

    public List<Parcel> parcelList;

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

}
