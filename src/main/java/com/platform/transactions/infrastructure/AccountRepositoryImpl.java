package com.platform.transactions.infrastructure;

import com.platform.transactions.domain.model.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class AccountRepositoryImpl implements IAccountRepository {

    @Override
    public Optional<Account> findById(String accountId) {
        return Optional.empty();
    }

    @Override
    public void update(Account account) {

    }

    @Override
    public Account create(Account account) {
        return null;
    }


}
