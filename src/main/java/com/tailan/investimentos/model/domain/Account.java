package com.tailan.investimentos.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tailan.investimentos.model.dtos.AccountDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_accounts")


public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) //CRIA UMA COLUNA USERID NA TABELA ACCOUNT, FK
    @JsonBackReference
    private User user;

    @OneToOne( cascade = CascadeType.ALL, mappedBy = "account")
    @PrimaryKeyJoinColumn
    private BillingAddress address;


    @OneToMany(mappedBy = "account")
    private List<AccountStock> accountStocks;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BillingAddress getAddress() {
        return address;
    }

    public void setAddress(BillingAddress address) {
        this.address = address;
    }

    public List<AccountStock> getAccountStocks() {
        return accountStocks;
    }

    public void setAccountStocks(List<AccountStock> accountStocks) {
        this.accountStocks = accountStocks;
    }

    public Account(Long id, String description, User user, BillingAddress address, List<AccountStock> accountStocks) {
        this.id = id;
        this.description = description;
        this.user = user;
        this.address = address;
        this.accountStocks = accountStocks;
    }




}
