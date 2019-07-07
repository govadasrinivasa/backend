package com.capco.capcopay.federal.request;

public class ViewAccountRequest {

	private Account_statement_req account_statement_req;

    public Account_statement_req getAccount_statement_req ()
    {
        return account_statement_req;
    }

    public void setAccount_statement_req (Account_statement_req account_statement_req)
    {
        this.account_statement_req = account_statement_req;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [account_statement_req = "+account_statement_req+"]";
    }
}
