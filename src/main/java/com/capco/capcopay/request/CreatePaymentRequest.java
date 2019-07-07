package com.capco.capcopay.request;

import java.util.Arrays;

public class CreatePaymentRequest {
	
	 private String debtAccountId;
	 private String creditAccountId;
	 private Double amount;
	public String getDebtAccountId() {
		return debtAccountId;
	}
	public void setDebtAccountId(String debtAccountId) {
		this.debtAccountId = debtAccountId;
	}
	public String getCreditAccountId() {
		return creditAccountId;
	}
	public void setCreditAccountId(String creditAccountId) {
		this.creditAccountId = creditAccountId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	 
}
