package com.parcel.entity;

import com.parcel.constants.ParcelType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Parcel {
    @Id
    private long parcelId;
    private double weight;
    private double height;
    private double width;
    private double length;
    private ParcelType parcelType;
}