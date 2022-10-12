package com.platform.transactions.domain.exception;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class NotEnoughMoneyException extends BaseClassException {

    private final String accountId;
    private final BigDecimal cash;
    private final BigDecimal value;

    public NotEnoughMoneyException(String accountId, BigDecimal cash, BigDecimal value) {
        this.cash = cash;
        this.accountId = accountId;
        this.value = value;
    }

    @Override
    protected String getCode() {
        return "TR-04";
    }


}
