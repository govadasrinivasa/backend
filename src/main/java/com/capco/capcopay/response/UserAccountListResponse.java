package com.capco.capcopay.response;

public class UserAccountListResponse {

	public BankAccountListResponse[] getAccountNumberList() {
		return accountNumberList;
	}
	public void setAccountNumberList(BankAccountListResponse[] accountNumberList) {
		this.accountNumberList = accountNumberList;
	}
	private String userSubscribedMsg;
	
	public String getUserSubscribedMsg() {
		return userSubscribedMsg;
	}
	public void setUserSubscribedMsg(String userSubscribedMsg) {
		this.userSubscribedMsg = userSubscribedMsg;
	}
	private BankAccountListResponse[] accountNumberList;

}
