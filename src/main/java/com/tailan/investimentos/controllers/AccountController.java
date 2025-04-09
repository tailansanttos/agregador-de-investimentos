package com.tailan.investimentos.controllers;

import com.tailan.investimentos.model.domain.Account;
import com.tailan.investimentos.model.dtos.AccountResponseDto;
import com.tailan.investimentos.model.dtos.AccountStockResponseDto;
import com.tailan.investimentos.model.dtos.AssociateStockDto;
import com.tailan.investimentos.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @Operation(summary = "Associar uma conta a uma ação", method = "POST")
    @ApiResponse(responseCode = "201", description = "Associação realizada com sucesso.")
    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Void> associateStockAccount(@PathVariable("accountId") Long accountId, @RequestBody AssociateStockDto dto){
       accountService.associateAccountStock(accountId,dto);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    @Operation(summary = "Retorna lista de contas.", method = "GET")
    @ApiResponse(responseCode = "200", description = "Lista de contas retornada.")
    public ResponseEntity<List<AccountResponseDto>> getAllAccountStocks(){
        var accounts = accountService.allAccounts();
        return ResponseEntity.ok(accounts);
    }



    @GetMapping("/{accountId}/stocks")
    @Operation(summary = "Retorna ações da conta", method = "GET")
    @ApiResponse(responseCode = "200", description = "Lista de ações da conta retornada.")
    public ResponseEntity<List<AccountStockResponseDto>> getAccountStocks(@PathVariable("accountId") Long accountId){
        accountService.getAccountStocks(accountId);
        return ResponseEntity.ok().body(accountService.getAccountStocks(accountId));
    }

}
