package com.capco.capcopay.federal.response;

public class Statement {

	private String txn_datetime;

    private String tran_date;

    private String tran_type;

    private String tran_id;

    private String seq_num;

    private String crdr_flg;

    private String tran_particular;

    private String ref_num;

    private String balance;

    private String debit;

    private String credit;

    private String tran_rmks;

    private String foracid;

    private String value_date;

    public String getTxn_datetime ()
    {
        return txn_datetime;
    }

    public void setTxn_datetime (String txn_datetime)
    {
        this.txn_datetime = txn_datetime;
    }

    public String getTran_date ()
    {
        return tran_date;
    }

    public void setTran_date (String tran_date)
    {
        this.tran_date = tran_date;
    }

    public String getTran_type ()
    {
        return tran_type;
    }

    public void setTran_type (String tran_type)
    {
        this.tran_type = tran_type;
    }

    public String getTran_id ()
    {
        return tran_id;
    }

    public void setTran_id (String tran_id)
    {
        this.tran_id = tran_id;
    }

    public String getSeq_num ()
    {
        return seq_num;
    }

    public void setSeq_num (String seq_num)
    {
        this.seq_num = seq_num;
    }

    public String getCrdr_flg ()
    {
        return crdr_flg;
    }

    public void setCrdr_flg (String crdr_flg)
    {
        this.crdr_flg = crdr_flg;
    }

    public String getTran_particular ()
    {
        return tran_particular;
    }

    public void setTran_particular (String tran_particular)
    {
        this.tran_particular = tran_particular;
    }

    public String getRef_num ()
    {
        return ref_num;
    }

    public void setRef_num (String ref_num)
    {
        this.ref_num = ref_num;
    }

    public String getBalance ()
    {
        return balance;
    }

    public void setBalance (String balance)
    {
        this.balance = balance;
    }

    public String getDebit ()
    {
        return debit;
    }

    public void setDebit (String debit)
    {
        this.debit = debit;
    }

    public String getCredit ()
    {
        return credit;
    }

    public void setCredit (String credit)
    {
        this.credit = credit;
    }

    public String getTran_rmks ()
    {
        return tran_rmks;
    }

    public void setTran_rmks (String tran_rmks)
    {
        this.tran_rmks = tran_rmks;
    }

    public String getForacid ()
    {
        return foracid;
    }

    public void setForacid (String foracid)
    {
        this.foracid = foracid;
    }

    public String getValue_date ()
    {
        return value_date;
    }

    public void setValue_date (String value_date)
    {
        this.value_date = value_date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [txn_datetime = "+txn_datetime+", tran_date = "+tran_date+", tran_type = "+tran_type+", tran_id = "+tran_id+", seq_num = "+seq_num+", crdr_flg = "+crdr_flg+", tran_particular = "+tran_particular+", ref_num = "+ref_num+", balance = "+balance+", debit = "+debit+", credit = "+credit+", tran_rmks = "+tran_rmks+", foracid = "+foracid+", value_date = "+value_date+"]";
    }
}
