package com.platform.transactions.entrypoint.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {

    private String transactionId;
    private String accountId;
    private TransactionType type;
    private BigDecimal value;

    public TransactionRequest(){}

}
