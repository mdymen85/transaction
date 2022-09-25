package com.platform.transactions.entrypoint.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountRequest {

    private String name;
    private BigDecimal cash;

}
