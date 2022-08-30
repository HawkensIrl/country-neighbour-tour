package com.example.countryneighbourtour.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class ExchangeRateService {

    @Value("${exchange.rate.api.url}")
    private String exchangeRateUrl;

    @Value("${exchange.rate.api.key}")
    private String exchangeRateApiKey;

    private final RestTemplate restTemplate;

    public ExchangeRateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double getExchangeRate(String fromCurrency, String toCurrency, double amount) throws JsonProcessingException {
        String uri = UriComponentsBuilder
                .fromUriString(exchangeRateUrl)
                .queryParam("to", toCurrency)
                .queryParam("from", fromCurrency)
                .queryParam("amount", amount)
                .build().toUriString();
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", exchangeRateApiKey);
        headers.set(HttpHeaders.USER_AGENT, "Mozilla/5.0");
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                request,
                String.class
        );

        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> map = mapper.readValue(response.getBody(), Map.class);
        //if converting eur to eur, exchange rate api returns int value, otherwise returns double
        return Double.parseDouble(""+map.get("result"));
    }

}
