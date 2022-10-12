package com.platform.transactions.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class LockManager {

    private final RedissonClient redissonClient;

    public <T> void lockAndProcess(Consumer<T> method, T object, String accountId) {

        var lock = redissonClient.getFairLock(accountId);

        try {

            log.info("Acquiring locking for account {}", accountId);

            lock.lock();

            log.info("Lock for account {} acquired", accountId);

            method.accept(object);

        }
        finally {

            lock.unlock();

            log.info("Releasing lock for account {}", accountId);

        }
    }

}
