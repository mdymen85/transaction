package com.platform.transactions.infrastructure;

import com.platform.transactions.domain.model.Transaction;

public interface TransactionRepository {

    public void save(Transaction transaction);

}
