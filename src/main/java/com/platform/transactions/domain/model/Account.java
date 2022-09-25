package com.platform.transactions.domain.model;

import com.platform.transactions.domain.exception.IncorrectValueException;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Builder
public class Account {

    private String accountId;
    private String name;
    private BigInteger cash;

    public Account add(BigInteger value) {
        if (value == null) {
            throw new IncorrectValueException();
        }
        cash.add(value);
        return this;
    }

    public Account substract(BigInteger value) {
        if (value == null) {
            throw new IncorrectValueException();
        }
        cash.subtract(value);
        return this;
    }

}
