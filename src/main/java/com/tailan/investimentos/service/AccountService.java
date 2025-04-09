package com.tailan.investimentos.service;

import com.tailan.investimentos.client.BrapiClient;
import com.tailan.investimentos.exceptions.UserNotFoundException;
import com.tailan.investimentos.model.domain.*;
import com.tailan.investimentos.model.dtos.AccountDto;
import com.tailan.investimentos.model.dtos.AccountResponseDto;
import com.tailan.investimentos.model.dtos.AccountStockResponseDto;
import com.tailan.investimentos.model.dtos.AssociateStockDto;
import com.tailan.investimentos.repositories.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
public class AccountService {
    //CRIADNO VARIAVEL DE AMBIENTE PARA O TOKEN
    @Value("${TOKEN}")
    private String TOKEN;



    private final AccountRepository accountRepository;
    private final BillingAddressRepository billingAddressRepository;
    private final UserService userService;
    private final AccountStockRepository accountStockRepository;
    private final StockRepository stockRepository;
    private final BrapiClient brapiClient;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, BillingAddressRepository billingAddressRepository, UserService userService, AccountStockRepository accountStockRepository, StockRepository stockRepository, BrapiClient brapiClient, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.billingAddressRepository = billingAddressRepository;
        this.userService = userService;
        this.accountStockRepository = accountStockRepository;
        this.stockRepository = stockRepository;
        this.brapiClient = brapiClient;
        this.userRepository = userRepository;
    }

    public Account createAccount(Long id, AccountDto accountDto) {
        User user = userService.getUser(id);

        Account account = new Account();
        account.setUser(user);
        account.setDescription(accountDto.description());
        account.setAddress(null);
        account.setAccountStocks(new ArrayList<>());
        accountRepository.save(account);

        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setStreet(accountDto.street());
        billingAddress.setNumber(accountDto.number());
        billingAddress.setAccount(account);
        billingAddressRepository.save(billingAddress);

        return account;
    }

    public List<AccountResponseDto> accounts(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Usúario não encontrado."));
        //FAZ O STREAM MAP PARA TRANSFORMAR O ACCOUNT EM UM ACCOUNT RESPONSE, UMA LISTA
       return user.getAccounts().stream().map(account -> new AccountResponseDto(account.getId(), account.getDescription())).toList();

    }


    public List<AccountResponseDto> allAccounts() {
       List<Account> accounts = accountRepository.findAll();
      return accounts.stream()
              .map(account -> new AccountResponseDto(account.getId(),account.getDescription())).toList();
    }

    public void associateAccountStock(Long accountId, AssociateStockDto dto) {
        //VERIFICA SE TEM UMA CONTA PELO ID
        var account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));

        //VERIFICA SE TEM UMA STOCK PELO ID

        var stock = stockRepository.findById(dto.stockId()).orElseThrow(() -> new RuntimeException("Stock not found"));

        //CRIA UM ID COM A CHAVE COMPOSTA, COM O ACCOUNT ID, E O STOCKID PASSADO NO DTO
        var id = new AccountStockId(accountId, stock.getStockId());

        //CRIA A ENTIDADE ACCOUNT STOCK, PASSANDO A CHAVE COMPOSTA, ACCOUNT, STOCK E  A QUANTIDADE
       var entity = new AccountStock(
               id,
               account,
               stock,
               dto.quantity()
       );

        accountStockRepository.save(entity);
    }

    public List<AccountStockResponseDto> getAccountStocks(Long accountId) {
        var account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));

       return account.getAccountStocks()
                .stream()
                .map(accountStock -> new AccountStockResponseDto
                        (accountStock.getStock().getStockId(),
                                accountStock.getQuantity(),
                                getTotal(accountStock.getQuantity(), accountStock.getStock().getStockId())))
                .toList();


    }

    private Double getTotal(Integer quantity, String stockId) {
       var response = brapiClient.getQuote(TOKEN, stockId);
        var price = response.results().getFirst().regularMarketPrice();

       return quantity *  price;
    }


}



