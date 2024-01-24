package com.parcel.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParcelApiError {
    private String errorMessage;
    private String errorCode;
}