package com.parcel.utils;

import com.parcel.constants.ParcelType;
import com.parcel.dto.ParcelDetails;
import com.parcel.entity.Parcel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ParcelMapperUtilsTest {

    @Test
    void testMapParcelDetailsToEntity() {
        ParcelDetails parcelDetails = new ParcelDetails();
        parcelDetails.setWeight(5.0);
        parcelDetails.setWidth(2.0);
        parcelDetails.setHeight(3.0);
        parcelDetails.setLength(4.0);
        double parcelCost = 50.0;
        ParcelType parcelType = ParcelType.SMALL_PARCEL;
        double weight = 5.0;
        Parcel result = ParcelMapperUtils.mapParcelDetailsToEntity(parcelDetails, parcelCost, weight, parcelType);
        assertEquals(parcelType, result.getParcelType());
        assertEquals(5.0,weight);
        assertEquals(2.0, result.getWidth());
        assertEquals(3.0, result.getHeight());
        assertEquals(4.0, result.getLength());
        assertEquals(parcelCost, result.getCost());
    }
}