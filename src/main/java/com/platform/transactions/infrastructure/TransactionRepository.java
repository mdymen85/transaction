package com.platform.transactions.infrastructure;

import com.platform.transactions.domain.model.Transaction;
import com.platform.transactions.infrastructure.entity.AccountEntity;
import com.platform.transactions.infrastructure.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {

    public void save(Transaction transaction);

}
