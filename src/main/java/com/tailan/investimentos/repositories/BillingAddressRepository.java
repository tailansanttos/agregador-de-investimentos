package com.tailan.investimentos.repositories;

import com.tailan.investimentos.model.domain.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingAddressRepository  extends JpaRepository<BillingAddress, Long> {
}
