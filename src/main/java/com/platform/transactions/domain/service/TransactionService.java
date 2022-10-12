package com.platform.transactions.domain.service;

import com.platform.transactions.domain.exception.AccountDoesNotExists;
import com.platform.transactions.domain.exception.NotEnoughMoneyException;
import com.platform.transactions.domain.model.Transaction;
import com.platform.transactions.domain.model.TransactionType;
import com.platform.transactions.domain.service.aop.AccountControl;
import com.platform.transactions.domain.service.aop.IdempotencyControl;
import com.platform.transactions.entrypoint.dto.TransactionRequest;
import com.platform.transactions.infrastructure.IAccountRepository;
import com.platform.transactions.infrastructure.TransactionRepository;
import com.platform.transactions.infrastructure.entity.TransactionEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final IAccountRepository accountRepository;
    private final LockManager lockManager;
    private final ModelMapper mapper;

    @IdempotencyControl
    @AccountControl
    public void createTransaction(TransactionRequest request) {

        var transaction = mapper.map(request, Transaction.class);
        lockManager.lockAndProcess(this::save, transaction, transaction.getAccountId());

    }

    private void save(Transaction transaction) {

        log.info("Starting transaction {} for account {} and value {}", transaction.getTransactionId(), transaction.getAccountId(), transaction.getValue());

        var delta = transaction.valueWithSign();

        var optAccount = accountRepository.findById(transaction.getAccountId());

        if (!optAccount.isPresent()) {
            throw new AccountDoesNotExists(transaction.getAccountId());
        }

        var account = optAccount.get();

        if (TransactionType.DEBIT.equals(transaction.getType()) &&
                account.getCash().compareTo(transaction.getValue()) < 0) {
            log.error("The account {} does not have enough money {} for the transaction value {}", transaction.getAccountId(), account.getCash(), transaction.getValue());
            throw new NotEnoughMoneyException(transaction.getAccountId(), account.getCash(), transaction.getValue());
        }

        log.info("Delta {} will be applied to the account {}", delta, transaction.getAccountId());

        accountRepository.update(transaction.getAccountId(), delta);

        log.info("Delta {} applied for the account {}", delta, transaction.getAccountId());

        var transactionEntity = TransactionEntity
                .builder()
                .transactionId(transaction.getTransactionId())
                .accountId(transaction.getAccountId())
                .value(transaction.getValue())
                .build();

        transactionRepository.save(transactionEntity);

        log.info("Transaction {} saved", transaction);

    }


}
