package com.parcel.entity;

import com.parcel.constants.ParcelType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="parcel")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parcel {
    @Id
    private long parcelId;
    private double weight;
    private double height;
    private double width;
    private double length;
    private double cost;
    private ParcelType parcelType;
}