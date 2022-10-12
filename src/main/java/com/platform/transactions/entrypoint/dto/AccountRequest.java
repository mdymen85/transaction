package com.platform.transactions.entrypoint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    private String name;
    private BigDecimal cash;

    public AccountRequest(BigDecimal cash, String name) {
        this.name = name;
        this.cash = cash;
    }
}
