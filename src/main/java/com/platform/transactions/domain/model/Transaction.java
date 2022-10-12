package com.platform.transactions.domain.model;

import com.platform.transactions.domain.exception.IncorrectValueException;
import lombok.*;
import org.apache.logging.log4j.util.Strings;

import java.math.BigDecimal;

@Data
@Getter
@NoArgsConstructor
@ToString
public class Transaction {

    private String transactionId;
    private String accountId;
    private BigDecimal value;
    private TransactionType type;

    @Builder
    public Transaction(String transactionId, String accountId, BigDecimal value, TransactionType transactionType) {

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IncorrectValueException(value);
        }

        if (Strings.isBlank(transactionId)) {
            throw new IncorrectValueException(transactionId);
        }

        if (Strings.isBlank(accountId)) {
            throw new IncorrectValueException(accountId);
        }

        this.transactionId = transactionId;
        this.accountId = accountId;
        this.value = value;
        this.type = transactionType;

    }

    public BigDecimal valueWithSign() {
        return TransactionType.CREDIT.equals(type) ? value : value.negate();
    }

}
