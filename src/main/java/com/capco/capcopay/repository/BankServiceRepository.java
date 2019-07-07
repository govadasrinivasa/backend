package com.capco.capcopay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capco.capcopay.entity.BankServices;

public interface BankServiceRepository extends JpaRepository<BankServices, Long> {

	List<BankServices> findByBankIdAndUserId(Long bankId, Long userId);
}
