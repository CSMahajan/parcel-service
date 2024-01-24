package com.parcel.exception;

import com.parcel.dto.ParcelDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ParcelExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> rejectParcel(ParcelException exception) {
        System.out.println(exception.getMessage());
        ParcelApiError parcelApiError = new ParcelApiError();
        parcelApiError.setErrorMessage(exception.getMessage());
        parcelApiError.setErrorCode(String.valueOf(HttpStatus.BAD_REQUEST));
        return new ResponseEntity<>(parcelApiError, HttpStatus.BAD_REQUEST);
    }
}