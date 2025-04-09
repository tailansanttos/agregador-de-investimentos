package com.tailan.investimentos.client;

import java.util.List;

public record BrapiResponse(List<StockResponseDto> results) {
}
