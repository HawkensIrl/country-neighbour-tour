package com.example.countryneighbourtour.dto;

public class CountryCurrencyInfoDto {

    private String currencyId;
    private double currencyAmount;

    public CountryCurrencyInfoDto(String currencyId, double currencyAmount) {
        this.currencyId = currencyId;
        this.currencyAmount = currencyAmount;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public double getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(double currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

}
