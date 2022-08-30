package com.example.countryneighbourtour.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorMessageDto {

    private String errorMessage;
    private HttpStatus status;
    private LocalDateTime localDateTime;

    public ErrorMessageDto(String errorMessage, HttpStatus status, LocalDateTime localDateTime) {
        this.errorMessage = errorMessage;
        this.status = status;
        this.localDateTime = localDateTime;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

}
