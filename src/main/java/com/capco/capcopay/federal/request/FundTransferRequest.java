package com.capco.capcopay.federal.request;

public class FundTransferRequest {

	private String Sender_Data;

    private String password;

    private String sendercd;

    private String ReferenceId;

    private BeneficiaryDetails BeneficiaryDetails;

    private String Remarks;

    private RemmiterDetails RemmiterDetails;

    private String respUrl;

    private Double Amount;

    private String tranDate;

    private String userid;

    public String getSender_Data ()
    {
        return Sender_Data;
    }

    public void setSender_Data (String Sender_Data)
    {
        this.Sender_Data = Sender_Data;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public String getSendercd ()
    {
        return sendercd;
    }

    public void setSendercd (String sendercd)
    {
        this.sendercd = sendercd;
    }

    public String getReferenceId ()
    {
        return ReferenceId;
    }

    public void setReferenceId (String ReferenceId)
    {
        this.ReferenceId = ReferenceId;
    }

    public BeneficiaryDetails getBeneficiaryDetails ()
    {
        return BeneficiaryDetails;
    }

    public void setBeneficiaryDetails (BeneficiaryDetails BeneficiaryDetails)
    {
        this.BeneficiaryDetails = BeneficiaryDetails;
    }

    public String getRemarks ()
    {
        return Remarks;
    }

    public void setRemarks (String Remarks)
    {
        this.Remarks = Remarks;
    }

    public RemmiterDetails getRemmiterDetails ()
    {
        return RemmiterDetails;
    }

    public void setRemmiterDetails (RemmiterDetails RemmiterDetails)
    {
        this.RemmiterDetails = RemmiterDetails;
    }

    public String getRespUrl ()
    {
        return respUrl;
    }

    public void setRespUrl (String respUrl)
    {
        this.respUrl = respUrl;
    }

    public Double getAmount ()
    {
        return Amount;
    }

    public void setAmount (Double Amount)
    {
        this.Amount = Amount;
    }

    public String getTranDate ()
    {
        return tranDate;
    }

    public void setTranDate (String tranDate)
    {
        this.tranDate = tranDate;
    }

    public String getUserid ()
    {
        return userid;
    }

    public void setUserid (String userid)
    {
        this.userid = userid;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Sender_Data = "+Sender_Data+", password = "+password+", sendercd = "+sendercd+", ReferenceId = "+ReferenceId+", BeneficiaryDetails = "+BeneficiaryDetails+", Remarks = "+Remarks+", RemmiterDetails = "+RemmiterDetails+", respUrl = "+respUrl+", Amount = "+Amount+", tranDate = "+tranDate+", userid = "+userid+"]";
    }
}
