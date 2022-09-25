package com.platform.transactions.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
@RequiredArgsConstructor
public class IdempotencyControlAspect {

    @Around("execution(* com.platform.transactions.domain.service.createTransaction(..)) && @annotation(com.platform.transactions.domain.service.IdempotencyControl)")
    public void idempotencyControl(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {



    }
}
