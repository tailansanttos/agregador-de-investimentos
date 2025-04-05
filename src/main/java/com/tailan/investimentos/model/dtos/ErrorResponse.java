package com.tailan.investimentos.model.dtos;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp, Integer status, String error, String message) {
}
