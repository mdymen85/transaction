package com.platform.transactions.domain.exception;

public class AccountDoesNotExists extends RuntimeException {
    public AccountDoesNotExists(String accountId, String transactionId) {
    }
}
