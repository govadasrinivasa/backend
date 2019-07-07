package com.capco.capcopay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capco.capcopay.entity.Payments;

public interface PaymentsRepository extends JpaRepository<Payments, Long> {

}
