package com.capco.capcopay.response;

public class LoginResponse {
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isSubscriptionFlag() {
		return subscriptionFlag;
	}

	public void setSubscriptionFlag(boolean subscriptionFlag) {
		this.subscriptionFlag = subscriptionFlag;
	}

	public String[] getBankNames() {
		return bankNames;
	}

	public void setBankNames(String[] bankNames) {
		this.bankNames = bankNames;
	}

	private String email;

    private String password;
    
    private String firstName;
	
	private String lastName;
	
	private String middleName;
	
	private String phoneNumber;
	
	private boolean subscriptionFlag = false; 
	
	private String[] bankNames=null;

}
