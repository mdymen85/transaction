package com.platform.transactions.domain.exception;

public class AccountDoesNotExists extends BaseClassException {

    private final String accountId;

    public AccountDoesNotExists(String accountId) {
        this.accountId = accountId;
    }

    @Override
    protected String getCode() {
        return "TR-01";
    }

    protected String getAccountId() {
        return this.accountId;
    }
}
