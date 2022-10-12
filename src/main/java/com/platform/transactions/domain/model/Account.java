package com.platform.transactions.domain.model;

import com.platform.transactions.domain.exception.IncorrectValueException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private String accountId;
    private String name;
    private BigDecimal cash;

    public Account add(BigDecimal value) {
        if (value == null) {
            throw new IncorrectValueException();
        }
        cash.add(value);
        return this;
    }

    public Account substract(BigDecimal value) {
        if (value == null) {
            throw new IncorrectValueException();
        }
        cash.subtract(value);
        return this;
    }

}
