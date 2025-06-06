package com.example.chatservice.model;

import lombok.Getter;

@Getter
public enum ResponseMessage {

    // Common
    VALIDATION_FAILED("VALIDATION_FAILED", "Validation failed"),
    VALIDATION_ONLY_ALLOW_IPAD("VALIDATION_ONLY_ALLOW_IPAD", "Login is only allowed from iPad devices"),

    // Reservation
    RESERVATION_NOT_FOUND("RESERVATION_NOT_FOUND", "Reservation not found");

    private final String code;
    private final String message;

    ResponseMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
