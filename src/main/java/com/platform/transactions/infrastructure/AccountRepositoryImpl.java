package com.platform.transactions.infrastructure;

import com.platform.transactions.domain.model.Account;
import com.platform.transactions.infrastructure.entity.AccountEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class AccountRepositoryImpl implements IAccountRepository {

    private final Repository accountRepository;

    @Override
    public Optional<Account> findById(String accountId) {
        var accountEntityOpt = accountRepository.findByAccountId(accountId);

        if (accountEntityOpt.isEmpty()) {
            return Optional.empty();
        }

        var accountEntity = accountEntityOpt.get();

        return Optional.ofNullable(Account.builder()
                .accountId(accountEntity.getAccountId())
                .cash(accountEntity.getCash())
                .name(accountEntity.getName())
                .build());

    }

    @Override
    public Account create(Account account) {

        var accountEntity = AccountEntity.builder()
                .accountId(account.getAccountId())
                .cash(account.getCash())
                .name(account.getName())
                .build();

        this.accountRepository.save(accountEntity);

        return account;
    }

    @Override
    public void update(String accountId, BigDecimal delta) {
        this.accountRepository.updateBalance(accountId, delta);
    }

    @Override
    public boolean existsByAccountId(String accountId) {
        return this.accountRepository.existsByAccountId(accountId);
    }


}
