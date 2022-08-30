package com.example.countryneighbourtour.controller;

import com.example.countryneighbourtour.dto.SampleTourDto;
import com.example.countryneighbourtour.service.TourCalculationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TourController {
    private final TourCalculationService tourCalculationService;

    public TourController(TourCalculationService tourCalculationService) {
        this.tourCalculationService = tourCalculationService;
    }

    @GetMapping("/")
    public String sendWelcomeMessage() {
        return "Welcome! Head to endpoint 'http://localhost:8080/tourAdvice?totalBudget={}&budgetPerCountry={}&startingCountryCode={}&currency={}}' for tour advice. " +
                "An example is: http://localhost:8080/tourAdvice?totalBudget=1000&budgetPerCountry=120&startingCountry=DE&currency=EUR";
    }

    @GetMapping("/tourAdvice")
    public SampleTourDto getTourAdvice(@RequestParam String startingCountryCode,
                                       @RequestParam double budgetPerCountry,
                                       @RequestParam double totalBudget,
                                       @RequestParam String currency) throws JsonProcessingException {
        return tourCalculationService.calculateTour(startingCountryCode, budgetPerCountry, totalBudget, currency);
    }

    @GetMapping("/user")
    public Map<String, Object> getUser(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        return oAuth2AuthenticationToken.getPrincipal().getAttributes();
    }


}
