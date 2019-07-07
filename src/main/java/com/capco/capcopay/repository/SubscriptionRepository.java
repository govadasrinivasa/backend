package com.capco.capcopay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capco.capcopay.entity.Subscription;
import com.capco.capcopay.entity.User;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

	Optional<Subscription> findBySubscriptionId(String subscriptionId);
	Subscription findByUserEmail(String email);
}
