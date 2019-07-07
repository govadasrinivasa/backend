package com.capco.capcopay.request;

public class SubscribeRequest {

	private Payments payments;

    private Accounts accounts;

    public Payments getPayments ()
    {
        return payments;
    }

    public void setPayments (Payments payments)
    {
        this.payments = payments;
    }

    public Accounts getAccounts ()
    {
        return accounts;
    }

    public void setAccounts (Accounts accounts)
    {
        this.accounts = accounts;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [payments = "+payments+", accounts = "+accounts+"]";
    }
}
