package com.capco.capcopay.federal.request;

public class Body {

	private String sender_cd;

    private String date;

    private String account_num;

    private String seq_num;

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

    public String getSeq_num ()
    {
        return seq_num;
    }

    public void setSeq_num (String seq_num)
    {
        this.seq_num = seq_num;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [sender_cd = "+sender_cd+", date = "+date+", account_num = "+account_num+", seq_num = "+seq_num+"]";
    }
}
