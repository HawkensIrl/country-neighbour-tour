package com.example.countryneighbourtour.service;

import com.example.countryneighbourtour.dto.CountryInfoDto;
import com.example.countryneighbourtour.dto.SampleTourDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class TourCalculationServiceTest {

    private CountryInfoService countryInfoService;

    private ExchangeRateService exchangeRateService;

    private TourCalculationService tourCalculationService;


    //sample test
    @Test
    public void calculateTour() throws JsonProcessingException {
        exchangeRateService = mock(ExchangeRateService.class);
        countryInfoService = mock(CountryInfoService.class);
        tourCalculationService = new TourCalculationService(countryInfoService, exchangeRateService);

        String startingCountry = "DE";
        List<CountryInfoDto> list = new ArrayList<>();
        list.add(new CountryInfoDto("Austria", "EUR", "AU"));
        list.add(new CountryInfoDto("Poland", "EUR", "PL"));
        list.add(new CountryInfoDto("Czech", "CZK", "CZ"));
        Mockito.when(countryInfoService.getSurroundingCountries(startingCountry)).thenReturn(list);
        Mockito.when(countryInfoService.addCurrencyCodeToListOfCountries(list)).thenReturn(list);
        Mockito.when(exchangeRateService.getExchangeRate("EUR", "EUR", 100)).thenReturn(100.0);
        Mockito.when(exchangeRateService.getExchangeRate("EUR", "CZK", 100)).thenReturn(400.0);

        SampleTourDto resultTour = tourCalculationService.calculateTour(startingCountry, 100, 1000, "EUR");

        assertEquals(3, resultTour.getNumberOfTours());
        assertEquals(100, resultTour.getLeftOverCash());
    }
}