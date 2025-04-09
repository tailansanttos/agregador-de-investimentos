package com.tailan.investimentos.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "tb_billingaddress")

public class BillingAddress {
    @Id
    private Long id;
    private String street;
    private Integer number;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;


    public BillingAddress() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BillingAddress(Long id, String street, Integer number, Account account) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.account = account;
    }


}
