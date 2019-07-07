package com.capco.capcopay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capco.capcopay.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {

	Bank findByBankName(String name);
}
