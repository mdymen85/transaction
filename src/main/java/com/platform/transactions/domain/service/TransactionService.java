package com.platform.transactions.domain.service;

import com.platform.transactions.domain.model.Transaction;
import com.platform.transactions.entrypoint.dto.TransactionRequest;
import com.platform.transactions.infrastructure.IAccountRepository;
import com.platform.transactions.infrastructure.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final IAccountRepository accountRepository;
    private final LockManager lockManager;

    @IdempotencyControl
    public void createTransaction(TransactionRequest request) {
        var mapper = new ModelMapper();
        var transaction = mapper.map(request, Transaction.class);

        lockManager.lockAndProcess(this::save, transaction, transaction.getAccountId());

    }

    private void save(Transaction transaction) {

        var accountOpt = this.accountRepository.findById(transaction.getAccountId());

        if (accountOpt.isEmpty()) {

        }

        var account = accountOpt.get();

        account.add(new BigInteger(transaction.getValue()+""));

        accountRepository.update(account);

        transactionRepository.save(transaction);

    }


}
