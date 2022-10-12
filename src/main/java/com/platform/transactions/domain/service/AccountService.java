package com.platform.transactions.domain.service;

import com.platform.transactions.domain.model.Account;
import com.platform.transactions.entrypoint.dto.AccountRequest;
import com.platform.transactions.entrypoint.dto.AccountResponse;
import com.platform.transactions.infrastructure.IAccountRepository;
import com.platform.transactions.infrastructure.entity.AccountEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final IAccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public AccountResponse create(AccountRequest request) {
        var account = modelMapper.map(request, Account.class);
        account.setAccountId(UUID.randomUUID().toString());
        account = accountRepository.create(account);

        return modelMapper.map(account, AccountResponse.class);
    }

    public AccountResponse load(String accountId) {
         var accountOpt = accountRepository.findById(accountId);

         if (accountOpt.isEmpty()) {
             Optional.empty();
         }

         var account = accountOpt.get();

         return AccountResponse.builder()
                 .cash(account.getCash())
                 .name(account.getName())
                 .accountId(account.getAccountId())
                 .build();


    }


}
