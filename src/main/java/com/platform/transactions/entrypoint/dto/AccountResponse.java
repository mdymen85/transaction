package com.platform.transactions.entrypoint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse extends AccountRequest{

    private String accountId;

    @Builder
    public AccountResponse(BigDecimal cash, String name, String accountId) {
        super(cash, name);
        this.accountId = accountId;
    }

}
