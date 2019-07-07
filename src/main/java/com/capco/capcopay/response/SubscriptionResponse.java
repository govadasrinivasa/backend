package com.capco.capcopay.response;

public class SubscriptionResponse {
	private String subscriptionId;

	@Override
	public String toString() {
		return "SubscriptionResponse [subscriptionId=" + subscriptionId + "]";
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
}
