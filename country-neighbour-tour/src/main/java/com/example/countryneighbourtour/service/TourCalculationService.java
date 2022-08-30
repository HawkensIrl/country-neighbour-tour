package com.example.countryneighbourtour.service;

import com.example.countryneighbourtour.dto.CountryCurrencyInfoDto;
import com.example.countryneighbourtour.dto.CountryInfoDto;
import com.example.countryneighbourtour.dto.SampleTourDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TourCalculationService {

    private final CountryInfoService countryInfoService;

    private final ExchangeRateService exchangeRateService;

    public TourCalculationService(CountryInfoService countryInfoService, ExchangeRateService exchangeRateService) {
        this.countryInfoService = countryInfoService;
        this.exchangeRateService = exchangeRateService;
    }

    public SampleTourDto calculateTour(String startingCountry,
                                       double budgetPerCountry,
                                       double totalBudget,
                                       String currency) throws JsonProcessingException {
        List<CountryInfoDto> countryInfoDtoList = countryInfoService.getSurroundingCountries(startingCountry);
        countryInfoDtoList = countryInfoService.addCurrencyCodeToListOfCountries(countryInfoDtoList);
        SampleTourDto sampleTourDto = calculateTourFinances(totalBudget, budgetPerCountry, countryInfoDtoList.size());
        sampleTourDto.setCountryNeighbourMap(calculateExchangeRate(currency, budgetPerCountry, countryInfoDtoList));
        return sampleTourDto;
    }

    private SampleTourDto calculateTourFinances(double totalBudget, double budgetPerCountry, int numberOfSurroundingCountries) {
        SampleTourDto sampleTourDto = new SampleTourDto();
        double costPerTour = budgetPerCountry*numberOfSurroundingCountries;
        int numberOfTours = (int)(totalBudget/costPerTour);
        double leftOverCash = totalBudget-(numberOfTours*costPerTour);
        sampleTourDto.setNumberOfTours(numberOfTours);
        sampleTourDto.setLeftOverCash(leftOverCash);
        return sampleTourDto;
    }

    private Map<String, CountryCurrencyInfoDto> calculateExchangeRate(String fromCurrency, double amount, List<CountryInfoDto> countryInfoDtoList) throws JsonProcessingException {
        Map<String, CountryCurrencyInfoDto> currencyMap = new HashMap<>();
        for(CountryInfoDto countryInfoDto: countryInfoDtoList) {
            currencyMap.put(countryInfoDto.getCountryName(), new CountryCurrencyInfoDto(countryInfoDto.getCurrencyCode(), exchangeRateService.getExchangeRate(fromCurrency, countryInfoDto.getCurrencyCode(), amount)));
        }
        return currencyMap;
    }

}
