package com.tailan.investimentos.service;

import com.tailan.investimentos.model.domain.AccountStock;
import com.tailan.investimentos.model.domain.AccountStockId;
import com.tailan.investimentos.model.domain.Stock;
import com.tailan.investimentos.model.dtos.AssociateStockDto;
import com.tailan.investimentos.model.dtos.StockDto;
import com.tailan.investimentos.repositories.AccountRepository;
import com.tailan.investimentos.repositories.AccountStockRepository;
import com.tailan.investimentos.repositories.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private final StockRepository stockRepository;
    private final AccountRepository accountRepository;
    private final AccountStockRepository accountStockRepository;

    public StockService(StockRepository stockRepository, AccountRepository accountRepository, AccountStockRepository accountStockRepository) {
        this.stockRepository = stockRepository;
        this.accountRepository = accountRepository;
        this.accountStockRepository = accountStockRepository;
    }

    public StockDto createStock(StockDto stockDto) {
        Stock stock = new Stock(
                stockDto.stockId(),
                stockDto.description()
        );
        stockRepository.save(stock);
        return new StockDto(stock.getStockId(), stock.getDescription());
    }

    public void deleteStock(String stockId) {
        stockRepository.deleteById(stockId);
    }
}
