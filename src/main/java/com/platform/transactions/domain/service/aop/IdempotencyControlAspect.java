package com.platform.transactions.domain.service.aop;

import com.platform.transactions.domain.exception.TransactionAlreadyProcessedException;
import com.platform.transactions.entrypoint.dto.TransactionRequest;
import com.platform.transactions.infrastructure.TransactionRepository;
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
public class IdempotencyControlAspect {

    private final TransactionRepository transactionRepository;

    @Around("execution(* com.platform.transactions.domain.service.TransactionService.createTransaction(..)) && @annotation(com.platform.transactions.domain.service.aop.IdempotencyControl)")
    public void idempotencyControl(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object[] args = proceedingJoinPoint.getArgs();
        var transaction = (TransactionRequest) args[0];

        log.info("Veryfing if the transaction {} has already been processed", transaction.getTransactionId());

        boolean exists = transactionRepository.existsByTransactionId(transaction.getTransactionId());

        if (exists) {
            throw new TransactionAlreadyProcessedException(transaction.getTransactionId());
        }

        log.info("Transaction {} is going to be processed", transaction.getTransactionId());

        proceedingJoinPoint.proceed(args);

    }
}
