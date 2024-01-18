package com.parcel.utils;

import com.parcel.constants.ParcelType;
import com.parcel.dto.ParcelDetails;
import com.parcel.entity.Parcel;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class ParcelMapperUtils {

    public Parcel mapParcelDetailsToEntity(ParcelDetails parcelDetails, double parcelCost, ParcelType parcelType) {
        return Parcel.builder().parcelId(generateUUId())
                .parcelType(parcelType)
                .weight(parcelDetails.getWeight())
                .width(parcelDetails.getWidth())
                .height(parcelDetails.getHeight())
                .length(parcelDetails.getLength())
                .cost(parcelCost)
                .build();
    }

    private static long generateUUId() {
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
}
