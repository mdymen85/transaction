package com.platform.transactions.infrastructure;

import com.platform.transactions.infrastructure.entity.AccountEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface Repository extends CrudRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByAccountId(String accountId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE AccountEntity  a SET a.cash = a.cash + :delta WHERE a.accountId = :accountId")
    void updateBalance(@Param("accountId") String accountId, @Param("delta") BigDecimal delta);
}
