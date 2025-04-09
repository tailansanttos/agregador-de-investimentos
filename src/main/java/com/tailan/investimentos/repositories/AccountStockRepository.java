package com.tailan.investimentos.repositories;

import com.tailan.investimentos.model.domain.AccountStock;
import com.tailan.investimentos.model.domain.AccountStockId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountStockRepository extends CrudRepository<AccountStock, AccountStockId> {
    void deleteById(String stockId);
}
