package com.platform.transactions.infrastructure;

import com.platform.transactions.domain.model.Account;

import java.math.BigDecimal;
import java.util.Optional;

public interface IAccountRepository {

    Optional<Account> findById(String accountId);

    Account create(Account account);

    void update(String accountId, BigDecimal delta);

}
