package com.parcel.wrapper;


import com.parcel.dto.CostDetails;
import com.parcel.dto.ParcelDetails;
import com.parcel.entity.Parcel;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ParcelCostDetailsWrapper {

    public CostDetails costDetails;
    public ParcelDetails parcelDetails;
    public Parcel parcel;
}
