package com.capco.capcopay.response;

import java.util.Arrays;

public class UserAccount {

	private String bankId;
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getIBAN() {
		return IBAN;
	}
	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getInfoTimeStamp() {
		return infoTimeStamp;
	}
	public void setInfoTimeStamp(String infoTimeStamp) {
		this.infoTimeStamp = infoTimeStamp;
	}
	public int getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(int interestRate) {
		this.interestRate = interestRate;
	}
	public String getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}
	public String getLastPaymentDate() {
		return lastPaymentDate;
	}
	public void setLastPaymentDate(String lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}
	public String getNextPaymentDate() {
		return nextPaymentDate;
	}
	public void setNextPaymentDate(String nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
	}
	public int getRemainingInstallments() {
		return remainingInstallments;
	}
	public void setRemainingInstallments(int remainingInstallments) {
		this.remainingInstallments = remainingInstallments;
	}
	public Balances[] getBalances() {
		return balances;
	}
	public void setBalances(Balances[] balances) {
		this.balances = balances;
	}
	private String accountId;
	private String accountAlias;
	@Override
	public String toString() {
		return "UserAccount [bankId=" + bankId + ", accountId=" + accountId + ", accountAlias=" + accountAlias
				+ ", accountType=" + accountType + ", accountName=" + accountName + ", IBAN=" + IBAN + ", currency="
				+ currency + ", infoTimeStamp=" + infoTimeStamp + ", interestRate=" + interestRate + ", maturityDate="
				+ maturityDate + ", lastPaymentDate=" + lastPaymentDate + ", nextPaymentDate=" + nextPaymentDate
				+ ", remainingInstallments=" + remainingInstallments + ", balances=" + Arrays.toString(balances) + "]";
	}
	public String getAccountAlias() {
		return accountAlias;
	}
	public void setAccountAlias(String accountAlias) {
		this.accountAlias = accountAlias;
	}
	private String accountType;
	private String accountName;
	private String IBAN;
	private String currency;
	private String infoTimeStamp;
	private int interestRate =0 ;
	private String maturityDate;
	private String lastPaymentDate;
	private String nextPaymentDate;
	private int remainingInstallments =0 ;
	private Balances[] balances;
	
}
