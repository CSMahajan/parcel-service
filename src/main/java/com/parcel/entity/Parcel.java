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
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcel parcel = (Parcel) o;
        return Double.compare(parcel.weight, weight) == 0 &&
                Double.compare(parcel.height, height) == 0 &&
                Double.compare(parcel.width, width) == 0 &&
                Double.compare(parcel.length, length) == 0 &&
                Double.compare(parcel.cost, cost) == 0 &&
                parcelType == parcel.parcelType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, height, width, length, cost, parcelType);
    }
}