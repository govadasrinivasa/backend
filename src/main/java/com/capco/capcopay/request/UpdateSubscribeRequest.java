package com.capco.capcopay.request;

public class UpdateSubscribeRequest {

	private Payments payments;

    private SelectedAccounts[] selectedAccounts;

    private Accounts accounts;

    public Payments getPayments ()
    {
        return payments;
    }

    public void setPayments (Payments payments)
    {
        this.payments = payments;
    }

    public SelectedAccounts[] getSelectedAccounts ()
    {
        return selectedAccounts;
    }

    public void setSelectedAccounts (SelectedAccounts[] selectedAccounts)
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

    @Override
    public String toString()
    {
        return "ClassPojo [payments = "+payments+", selectedAccounts = "+selectedAccounts+", accounts = "+accounts+"]";
    }
}
