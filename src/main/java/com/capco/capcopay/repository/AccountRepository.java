package com.capco.capcopay.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capco.capcopay.entity.Subscription;
import com.capco.capcopay.entity.UserAccounts;

public interface AccountRepository extends JpaRepository<UserAccounts, Long> {

	List<UserAccounts> findByUserEmail(String email);
	List<UserAccounts> findBySubscriptionId(Long subId);
}
