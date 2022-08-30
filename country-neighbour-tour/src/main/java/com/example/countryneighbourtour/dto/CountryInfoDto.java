package com.example.countryneighbourtour.dto;

public class CountryInfoDto {

    private String countryName;
    private String currencyCode;
    private String countryCode;

    public CountryInfoDto(){}

    public CountryInfoDto(String countryName, String currencyCode, String countryCode) {
        this.countryName = countryName;
        this.currencyCode = currencyCode;
        this.countryCode =  countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
