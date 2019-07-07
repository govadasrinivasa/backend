package com.capco.capcopay.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity(name="BankServices")
@Table(name = "CAPCOPAY_BANK_SERVICES")
@DynamicUpdate
public class BankServices implements Serializable {
 
    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	public BankServices() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBankServiceName() {
		return bankServiceName;
	}

	public void setBankServiceName(String bankServiceName) {
		this.bankServiceName = bankServiceName;
	}

	public boolean isServiceSubscribed() {
		return serviceSubscribed;
	}

	public void setServiceSubscribed(boolean serviceSubscribed) {
		this.serviceSubscribed = serviceSubscribed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private String bankServiceName;
	private boolean serviceSubscribed;
	
	@ManyToOne
    private User user;
	
	@ManyToOne
    private Bank bank;


	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
}
