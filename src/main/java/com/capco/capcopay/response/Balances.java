package com.capco.capcopay.response;

public class Balances {

	private String amount;
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getBalanceType() {
		return balanceType;
	}
	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}
	private String balanceType;
	@Override
	public String toString() {
		return "Balances [amount=" + amount + ", balanceType=" + balanceType + "]";
	}
}
