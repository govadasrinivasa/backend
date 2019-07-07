package com.capco.capcopay.response;

import java.util.Arrays;

public class UserAccounts {

	private UserAccount[] account;

	public UserAccount[] getAccount() {
		return account;
	}

	public void setAccount(UserAccount[] account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "UserAccounts [account=" + Arrays.toString(account) + "]";
	}


}
