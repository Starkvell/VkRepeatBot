package com.example.vkrepeatbot.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FailedMessageException extends RuntimeException {
    private final HttpStatus status;
    private final int errorCode;
    private final String errorMessage;

    public FailedMessageException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.errorCode = 0;  // Используем 0 или любое значение, если код ошибки не передается
        this.errorMessage = message;
    }

    public FailedMessageException(HttpStatus status, int errorCode, String errorMessage) {
        super("VK API Error: Code " + errorCode + " - " + errorMessage);
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}