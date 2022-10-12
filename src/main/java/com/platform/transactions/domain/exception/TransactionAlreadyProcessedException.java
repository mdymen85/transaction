package com.platform.transactions.domain.exception;

public class TransactionAlreadyProcessedException extends BaseClassException {

    private final String transactionId;

    public TransactionAlreadyProcessedException(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    protected String getCode() {
        return "TR-02";
    }

    protected String getTransactionId() {
        return this.transactionId;
    }
}