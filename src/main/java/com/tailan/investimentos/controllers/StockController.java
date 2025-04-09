package com.tailan.investimentos.controllers;

import com.tailan.investimentos.model.domain.Stock;
import com.tailan.investimentos.model.dtos.StockDto;
import com.tailan.investimentos.model.dtos.UserDto;
import com.tailan.investimentos.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocks")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar ação", method = "POST")
    @ApiResponse(responseCode = "201", description = "Ação cadastrada com sucesso.")
    public ResponseEntity<StockDto> createStock(@RequestBody StockDto stockDto) {
        var stock = stockService.createStock(stockDto);
        return new ResponseEntity<>(stock, HttpStatus.CREATED);
    }

    @DeleteMapping("/{stockId}")
    @Operation(summary = "Deletar uma ação", method = "DELETE")
    @ApiResponse(responseCode = "204", description = "Ação deletada com sucesso")
    private ResponseEntity<Void> deleteStock(@PathVariable("stockId") String stockId) {
        stockService.deleteStock(stockId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
