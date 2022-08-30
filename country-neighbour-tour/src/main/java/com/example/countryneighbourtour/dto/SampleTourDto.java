package com.example.countryneighbourtour.dto;

import java.util.Map;

public class SampleTourDto {

    private int numberOfTours;

    private double leftOverCash;

    private Map<String, CountryCurrencyInfoDto> countryNeighbourMap;

    public SampleTourDto(){}

    public SampleTourDto(int numberOfTours, double leftOverCash, Map<String, CountryCurrencyInfoDto> countryNeighbourMap) {
        this.numberOfTours = numberOfTours;
        this.leftOverCash = leftOverCash;
        this.countryNeighbourMap = countryNeighbourMap;
    }

    public int getNumberOfTours() {
        return numberOfTours;
    }

    public void setNumberOfTours(int numberOfTours) {
        this.numberOfTours = numberOfTours;
    }

    public double getLeftOverCash() {
        return leftOverCash;
    }

    public void setLeftOverCash(double leftOverCash) {
        this.leftOverCash = leftOverCash;
    }

    public Map<String, CountryCurrencyInfoDto> getCountryNeighbourMap() {
        return countryNeighbourMap;
    }

    public void setCountryNeighbourMap(Map<String, CountryCurrencyInfoDto> countryNeighbourMap) {
        this.countryNeighbourMap = countryNeighbourMap;
    }
}
