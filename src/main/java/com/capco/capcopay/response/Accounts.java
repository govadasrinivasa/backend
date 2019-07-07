package com.capco.capcopay.response;

public class Accounts {

	private String checkFundsAvailability;

    private String balance;

    private String transactionHistory;

    private String details;

    public String getCheckFundsAvailability ()
    {
        return checkFundsAvailability;
    }

    public void setCheckFundsAvailability (String checkFundsAvailability)
    {
        this.checkFundsAvailability = checkFundsAvailability;
    }

    public String getBalance ()
    {
        return balance;
    }

    public void setBalance (String balance)
    {
        this.balance = balance;
    }

    public String getTransactionHistory ()
    {
        return transactionHistory;
    }

    public void setTransactionHistory (String transactionHistory)
    {
        this.transactionHistory = transactionHistory;
    }

    public String getDetails ()
    {
        return details;
    }

    public void setDetails (String details)
    {
        this.details = details;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [checkFundsAvailability = "+checkFundsAvailability+", balance = "+balance+", transactionHistory = "+transactionHistory+", details = "+details+"]";
    }
}
