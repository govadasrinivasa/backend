package com.capco.capcopay.federal.response;

public class Account_statement_resp {

	private String sender_cd;

    private String date;

    private String account_num;

    private String opening_bal;

    private String account_name;

    private Statement[] statement;

    private String last_seq_num;

    public String getSender_cd ()
    {
        return sender_cd;
    }

    public void setSender_cd (String sender_cd)
    {
        this.sender_cd = sender_cd;
    }

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public String getAccount_num ()
    {
        return account_num;
    }

    public void setAccount_num (String account_num)
    {
        this.account_num = account_num;
    }

    public String getOpening_bal ()
    {
        return opening_bal;
    }

    public void setOpening_bal (String opening_bal)
    {
        this.opening_bal = opening_bal;
    }

    public String getAccount_name ()
    {
        return account_name;
    }

    public void setAccount_name (String account_name)
    {
        this.account_name = account_name;
    }

    public Statement[] getStatement ()
    {
        return statement;
    }

    public void setStatement (Statement[] statement)
    {
        this.statement = statement;
    }

    public String getLast_seq_num ()
    {
        return last_seq_num;
    }

    public void setLast_seq_num (String last_seq_num)
    {
        this.last_seq_num = last_seq_num;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [sender_cd = "+sender_cd+", date = "+date+", account_num = "+account_num+", opening_bal = "+opening_bal+", account_name = "+account_name+", statement = "+statement+", last_seq_num = "+last_seq_num+"]";
    }
}
