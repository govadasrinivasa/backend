package com.capco.capcopay.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;

@Entity(name="Bank")
@Table(name = "CAPCOPAY_BANK", uniqueConstraints={@UniqueConstraint(columnNames ={"bankId"})})
@DynamicUpdate
public class Bank implements Serializable {
 
    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public boolean isBankSubscribed() {
		return bankSubscribed;
	}
	public void setBankSubscribed(boolean bankSubscribed) {
		this.bankSubscribed = bankSubscribed;
	}
	
	private String bankId;
	private String bankName;
	private boolean bankSubscribed;
	
	@OneToMany(mappedBy = "bank")
    private List<BankServices> bankServices;

	public List<BankServices> getBankServices() {
		return bankServices;
	}
	public void setBankServices(List<BankServices> bankServices) {
		this.bankServices = bankServices;
	}
	public Bank() {}
	
	public Bank(String bankId, String bankName, boolean bankSubscribed) {
	  this.bankId = bankId;
	  this.bankName = bankName;
	  this.bankSubscribed = bankSubscribed;
    }

}
