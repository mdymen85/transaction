package com.platform.transactions.domain.service.aop;

import com.platform.transactions.domain.exception.AccountDoesNotExists;
import com.platform.transactions.domain.exception.TransactionAlreadyProcessedException;
import com.platform.transactions.entrypoint.dto.TransactionRequest;
import com.platform.transactions.infrastructure.IAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@RequiredArgsConstructor
@Component
public class AccountControlAspect {

    private final IAccountRepository accountRepository;

    @Around("execution(* com.platform.transactions.domain.service.TransactionService.createTransaction(..)) && @annotation(com.platform.transactions.domain.service.aop.AccountControl)")
    public void idempotencyControl(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object[] args = proceedingJoinPoint.getArgs();
        var transaction = (TransactionRequest) args[0];

        log.info("Verifying if account {} exists", transaction.getAccountId());

        var accountExists = this.accountRepository.existsByAccountId(transaction.getAccountId());

        if (!accountExists) {
            log.error("Account {} does not exists", transaction.getAccountId());
            throw new AccountDoesNotExists(transaction.getAccountId());
        }

        log.info("Account {} found", transaction.getAccountId());

        proceedingJoinPoint.proceed(args);

    }

}
