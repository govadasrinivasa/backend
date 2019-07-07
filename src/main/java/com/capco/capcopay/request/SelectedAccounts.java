package com.capco.capcopay.request;

public class SelectedAccounts {

	private String accountId;

    public String getAccountId ()
    {
        return accountId;
    }

    public void setAccountId (String accountId)
    {
        this.accountId = accountId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [accountId = "+accountId+"]";
    }
}
