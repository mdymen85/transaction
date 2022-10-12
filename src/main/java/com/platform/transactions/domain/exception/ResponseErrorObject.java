package com.platform.transactions.domain.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseErrorObject {

    private final String code;
    private final String message;
    private final LocalDateTime timestamp;

    @Builder
    public ResponseErrorObject(String code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }


}