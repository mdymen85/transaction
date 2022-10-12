package com.platform.transactions.domain.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class InformationControllerAdvice {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(AccountDoesNotExists.class)
    public ResponseEntity<ResponseErrorObject> accountDoesNotExistsException(AccountDoesNotExists error) {
        return new ResponseError(error.getCode(),
                messageSource.getMessage(error.getCode(), new Object[] {error.getAccountId()}, Locale.ENGLISH),
                HttpStatus.BAD_REQUEST)
                .getResponseErrorObject();
    }

    @ExceptionHandler(TransactionAlreadyProcessedException.class)
    public ResponseEntity<ResponseErrorObject> transactionAlreadyProcessedException(TransactionAlreadyProcessedException error) {
        return new ResponseError(error.getCode(),
                messageSource.getMessage(error.getCode(), new Object[] {error.getTransactionId()}, Locale.ENGLISH),
                HttpStatus.BAD_REQUEST)
                .getResponseErrorObject();
    }

    @ExceptionHandler(IncorrectValueException.class)
    public ResponseEntity<ResponseErrorObject> incorrectValueException(IncorrectValueException error) {
        return new ResponseError(error.getCode(),
                messageSource.getMessage(error.getCode(), new Object[] {error.getValue()}, Locale.ENGLISH),
                HttpStatus.BAD_REQUEST)
                .getResponseErrorObject();
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<ResponseErrorObject> notEnoughMoneyException(NotEnoughMoneyException error) {
        return new ResponseError(error.getCode(),
                messageSource.getMessage(error.getCode(), new Object[] {error.getAccountId(), error.getCash(), error.getValue()}, Locale.ENGLISH),
                HttpStatus.BAD_REQUEST)
                .getResponseErrorObject();
    }

}

