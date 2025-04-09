package com.tailan.investimentos.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public record AccountDto(String description, String  street, Integer number) {
}
