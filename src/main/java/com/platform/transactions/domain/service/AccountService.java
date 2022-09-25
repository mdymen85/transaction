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

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final IAccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public AccountResponse create(AccountRequest request) {
        var account = modelMapper.map(request, Account.class);
        account = accountRepository.create(account);
    }

    public AccountResponse load(AccountResponse response) {
        return null;
    }


}
