package com.platform.transactions.domain.model;

import com.platform.transactions.domain.exception.IncorrectValueException;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.math.BigDecimal;

@Data
@Getter
public class Transaction {

    private String transactionId;
    private String accountId;
    private BigDecimal value;

    @Builder
    public Transaction(String transactionId, String accountId, BigDecimal value) {

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IncorrectValueException();
        }

        if (Strings.isBlank(transactionId)) {
            throw new IncorrectValueException();
        }

        if (Strings.isBlank(accountId)) {
            throw new IncorrectValueException();
        }

        this.transactionId = transactionId;
        this.accountId = accountId;
        this.value = value;

    }

}
