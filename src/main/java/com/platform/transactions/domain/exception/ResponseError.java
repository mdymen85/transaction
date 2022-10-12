package com.platform.transactions.domain.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ResponseError {

    private ResponseEntity<ResponseErrorObject> responseErrorObject;

    @Builder
    public ResponseError(String code, String message, HttpStatus httpStatus) {
        var responseErrorObject = ResponseErrorObject
                .builder()
                .code(code)
                .message(message)
                .build();

        this.responseErrorObject = new ResponseEntity<ResponseErrorObject>(responseErrorObject, httpStatus);
    }

}