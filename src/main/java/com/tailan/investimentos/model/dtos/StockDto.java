package com.tailan.investimentos.model.dtos;

import com.tailan.investimentos.model.domain.Stock;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public record StockDto(String stockId, String description) {
}
