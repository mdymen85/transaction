package com.platform.transactions.infrastructure;

import com.platform.transactions.domain.model.Account;

import java.util.Optional;

public interface IAccountRepository {

    Optional<Account> findById(String accountId);

    void update(Account account);

    Account create(Account account);
}
