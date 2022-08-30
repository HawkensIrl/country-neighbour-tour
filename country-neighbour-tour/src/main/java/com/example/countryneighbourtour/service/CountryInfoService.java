package com.example.countryneighbourtour.service;

import com.example.countryneighbourtour.dto.CountryInfoDto;
import com.example.countryneighbourtour.exception.CountryNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class CountryInfoService {

    @Value("${country.info.api.url}")
    private String countryInfoUrl;

    @Value("${country.info.neighbours.api.url}")
    private String countryInfoNeighboursUrl;

    @Value("${country.info.api.username}")
    private String userName;

    private final RestTemplate restTemplate;

    public CountryInfoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CountryInfoDto getCountryInfo(String countryCode) {
        String uri = UriComponentsBuilder
                .fromUriString(countryInfoUrl)
                .queryParam("formatted", "true")
                .queryParam("country", countryCode)
                .queryParam("username", userName)
                .build().toUriString();

        return getListOfCountriesFromMap(restTemplate.getForObject(uri, LinkedHashMap.class), countryCode).get(0);
    }

    public List<CountryInfoDto> getSurroundingCountries(String countryCode) {
        String uri = UriComponentsBuilder
                .fromUriString(countryInfoNeighboursUrl)
                .queryParam("formatted", "true")
                .queryParam("countryCode", countryCode)
                .queryParam("username", userName)
                .build().toUriString();

        return getListOfCountriesFromMap(restTemplate.getForObject(uri, LinkedHashMap.class), countryCode);
    }

    public List<CountryInfoDto> addCurrencyCodeToListOfCountries(List<CountryInfoDto> countryInfoDtoList) {
        List<CountryInfoDto> countryCodeListWithCurrency = new ArrayList<>();
        countryInfoDtoList.forEach(countryInfo -> {
            countryCodeListWithCurrency.add(getCountryInfo(countryInfo.getCountryCode()));
        });
        return countryCodeListWithCurrency;
    }

    public List<CountryInfoDto> getListOfCountriesFromMap(LinkedHashMap resultMap, String countryCode) {
        List<CountryInfoDto> countryInfoDtoList = new ArrayList<>();
        List<LinkedHashMap> resultList = (ArrayList)resultMap.get("geonames");
        if(null == resultList || resultList.isEmpty()) {
            throw new CountryNotFoundException("Country not found with countryCode: " + countryCode);
        }
        resultList.forEach(LinkedHashMap -> {
            countryInfoDtoList.add(new CountryInfoDto((String)LinkedHashMap.get("countryName"), (String)LinkedHashMap.get("currencyCode"), (String)LinkedHashMap.get("countryCode")));
        });
        return countryInfoDtoList;
    }
}
