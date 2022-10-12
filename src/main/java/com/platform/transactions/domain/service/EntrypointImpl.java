package com.platform.transactions.domain.service;

import com.platform.transactions.entrypoint.IEntrypoint;
import com.platform.transactions.entrypoint.dto.AccountRequest;
import com.platform.transactions.entrypoint.dto.AccountResponse;
import com.platform.transactions.entrypoint.dto.TransactionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EntrypointImpl implements IEntrypoint {

    private final TransactionService transactionService;
    private final AccountService accountService;

    @Override
    public TransactionRequest createTransaction(TransactionRequest request) {

        transactionService.createTransaction(request);

        return request;
    }

    @Override
    public AccountResponse createAccount(AccountRequest request) {

        log.info("Creating account {}", request);

        return accountService.create(request);

    }

    @Override
    public AccountResponse loadAccount(String accountId) {
        return accountService.load(accountId);
    }
}
