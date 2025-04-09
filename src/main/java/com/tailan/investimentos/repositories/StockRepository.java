package com.tailan.investimentos.repositories;

import com.tailan.investimentos.model.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
}
