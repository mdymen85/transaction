package com.platform.transactions.domain.exception;

import java.math.BigDecimal;

public class IncorrectValueException extends BaseClassException {

    private final Object value;

    public IncorrectValueException(Object value) {
        this.value = value;
    }

    @Override
    protected String getCode() {
        return "TR-01";
    }

    protected Object getValue() {
        return this.value;
    }
}
