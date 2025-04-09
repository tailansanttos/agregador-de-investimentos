package com.tailan.investimentos.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;

import java.util.Objects;

@Embeddable

public class AccountStockId {
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "stock_id")
    private String stockId;

    public AccountStockId() {
    }

    public AccountStockId(Long accountId, String stockId) {
        this.accountId = accountId;
        this.stockId = stockId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AccountStockId that = (AccountStockId) o;
        return Objects.equals(accountId, that.accountId) && Objects.equals(stockId, that.stockId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, stockId);
    }
}
