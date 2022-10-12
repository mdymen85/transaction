package com.platform.transactions.entrypoint;

import com.platform.transactions.entrypoint.dto.AccountRequest;
import com.platform.transactions.entrypoint.dto.AccountResponse;
import com.platform.transactions.entrypoint.dto.TransactionRequest;

public interface IEntrypoint {

    void createTransaction(TransactionRequest request);

    AccountResponse createAccount(AccountRequest request);

    AccountResponse loadAccount(String accountId);
}
