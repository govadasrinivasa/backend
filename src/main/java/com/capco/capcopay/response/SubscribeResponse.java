package com.capco.capcopay.response;

public class SubscribeResponse {

	private Duration duration;

    private Payments payments;

    private String description;

    private String[] selectedAccounts;

    private Accounts accounts;

    private String subscriptionId;

    private String status;

    public Duration getDuration ()
    {
        return duration;
    }

    public void setDuration (Duration duration)
    {
        this.duration = duration;
    }

    public Payments getPayments ()
    {
        return payments;
    }

    public void setPayments (Payments payments)
    {
        this.payments = payments;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String[] getSelectedAccounts ()
    {
        return selectedAccounts;
    }

    public void setSelectedAccounts (String[] selectedAccounts)
    {
        this.selectedAccounts = selectedAccounts;
    }

    public Accounts getAccounts ()
    {
        return accounts;
    }

    public void setAccounts (Accounts accounts)
    {
        this.accounts = accounts;
    }

    public String getSubscriptionId ()
    {
        return subscriptionId;
    }

    public void setSubscriptionId (String subscriptionId)
    {
        this.subscriptionId = subscriptionId;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [duration = "+duration+", payments = "+payments+", description = "+description+", selectedAccounts = "+selectedAccounts+", accounts = "+accounts+", subscriptionId = "+subscriptionId+", status = "+status+"]";
    }
}
