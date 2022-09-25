package com.platform.transactions.entrypoint;

import com.platform.transactions.entrypoint.dto.AccountRequest;
import com.platform.transactions.entrypoint.dto.AccountResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountController {

    private final IEntrypoint entrypoint;

    @RequestMapping(value = "/v1/account", method = RequestMethod.POST)
    public AccountResponse createAccount(@RequestBody AccountRequest request) {
        return entrypoint.createAccount(request);
    }

    @RequestMapping(value = "/v1/account/{accountId}", method = RequestMethod.GET)
    public AccountResponse createAccount(@PathVariable String accountId) {
        return entrypoint.loadAccount(accountId);
    }
}
