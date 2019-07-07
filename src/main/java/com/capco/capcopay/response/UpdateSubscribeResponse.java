package com.capco.capcopay.response;

import java.util.Arrays;

public class UpdateSubscribeResponse {

	public String getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	private String subscriptionId;
	private String status;
	private String description;
	
	
	public SelectedAccounts[] getSelectedAccounts() {
		return selectedAccounts;
	}
	public void setSelectedAccounts(SelectedAccounts[] selectedAccounts) {
		this.selectedAccounts = selectedAccounts;
	}
	public Accounts getAccounts() {
		return accounts;
	}
	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}
	public Payments getPayments() {
		return payments;
	}
	public void setPayments(Payments payments) {
		this.payments = payments;
	}
	public Duration getDuration() {
		return duration;
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	private SelectedAccounts[] selectedAccounts;
	private Accounts accounts;
	private Payments payments;
	private Duration duration;


	@Override
	public String toString() {
		return "UpdateSubscribeResponse [subscriptionId=" + subscriptionId + ", status=" + status + ", description="
				+ description + ", selectedAccounts=" + Arrays.toString(selectedAccounts) + ", accounts=" + accounts
				+ ", payments=" + payments + ", duration=" + duration + "]";
	}

	
	
}
