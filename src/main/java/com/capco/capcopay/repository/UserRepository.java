package com.capco.capcopay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capco.capcopay.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
