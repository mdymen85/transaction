package com.platform.transactions.entrypoint.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountRequest {

    private String name;
    private BigDecimal cash;

    public AccountRequest(BigDecimal cash, String name) {
        this.name = name;
        this.cash = cash;
    }
}
