package com.example.countryneighbourtour.exception;


public class CountryNotFoundException extends RuntimeException {

    public CountryNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
