package com.tailan.investimentos.controllers;

import com.tailan.investimentos.model.domain.Account;
import com.tailan.investimentos.model.domain.User;
import com.tailan.investimentos.model.dtos.AccountDto;
import com.tailan.investimentos.model.dtos.AccountResponseDto;
import com.tailan.investimentos.model.dtos.UserDto;
import com.tailan.investimentos.service.AccountService;
import com.tailan.investimentos.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/users")
@Tag(name = "Agregador de investimentos")
public class UserController {

    private final UserService userService;
    private final AccountService accountService;

    public UserController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }


    @PostMapping
    @Operation(summary = "Cadastrar usuário", method = "POST")
    @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso.")
    @ApiResponse(responseCode = "400", description = "Email passado já cadastrado.")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto newUser = userService.createUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Retorna usúario pelo ID", method = "GET")
    @ApiResponse(responseCode = "200", description = "Usúario retornado")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping
    @Operation(summary = "Retorna lista de usúarios cadastrados.", method = "GET")
    @ApiResponse(responseCode = "200", description = "Lista de usúario retornado.")
    public ResponseEntity<List<UserDto>> getUsers() {
       var users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um usuário", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })

    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserDto userDto) {
        var user = userService.updateUser(id, userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um usuário", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/accounts")
    @Operation(summary = "Cria uma nova conta para um usuário", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Conta criada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<AccountDto> createAccount(@PathVariable("id") Long id, @RequestBody AccountDto  accountDto) {
       Account account = accountService.createAccount(id, accountDto);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}/accounts")
    @Operation(summary = "Retorna as contas de um usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contas retornadas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<List<AccountResponseDto>> getAccounts(@PathVariable("id") Long id) {
        var accounts = accountService.accounts(id);
        return ResponseEntity.ok(accounts);
    }
}
