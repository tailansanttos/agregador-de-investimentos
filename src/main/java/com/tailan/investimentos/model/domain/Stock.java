package com.tailan.investimentos.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.Objects;

@Entity
@Table(name = "tb_stocks")
@EqualsAndHashCode
public class Stock {
    @Id
    private String stockId;
    private String description;

    public Stock() {

    }

    public Stock(String stockId, String description) {
        this.stockId = stockId;
        this.description = description;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
