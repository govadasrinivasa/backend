package com.capco.capcopay.response;

public class BankAccountListResponse {

	private String bankName;
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	private String[] accountId;

	public String[] getAccountId() {
		return accountId;
	}

	public void setAccountId(String[] accountId) {
		this.accountId = accountId;
	}
}
