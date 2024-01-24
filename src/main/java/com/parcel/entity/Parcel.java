package com.parcel.entity;

import com.parcel.constants.ParcelType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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
    @Column(name="parcelId")
    private long parcelId;
    @Column(name="weight")
    private double weight;
    @Column(name="height")
    private double height;
    @Column(name="width")
    private double width;
    @Column(name="length")
    private double length;
    @Column(name="cost")
    private double cost;
    @Column(name="parcelType")
    private ParcelType parcelType;
}