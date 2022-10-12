package com.platform.transactions.entrypoint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {

    private String transactionId;
    private String accountId;
    private TransactionType type;
    private BigDecimal value;

    public TransactionRequest(){}

    public TransactionRequest(String transactionId, String accountId, TransactionType type, BigDecimal value) {

        this.transactionId = transactionId;
        this.accountId = accountId;
        this.type = type;
        this.value = value;

    }
}
