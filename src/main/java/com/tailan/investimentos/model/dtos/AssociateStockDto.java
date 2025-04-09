package com.tailan.investimentos.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public record AssociateStockDto(String stockId, Integer quantity) {
}
