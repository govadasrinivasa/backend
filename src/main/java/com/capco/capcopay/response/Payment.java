package com.capco.capcopay.response;

public class Payment {

	private String paymentId ;
	private String transactionTime;
	private String charges;
	private String totalCharges;
	private String endToEndId;
	private String paymentDetails;
	private String terminalId;
	private String branch;
    private String  RUB;
    private String executionDate;
    private String valueDate;
    private String totalDebitAmount;
    private String attachments;
    
    public Payment() {
    	
    }
    
	public Payment(String paymentId, String transactionTime) {
		this.paymentId= paymentId;
		this.transactionTime = transactionTime;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}
	public String getCharges() {
		return charges;
	}
	public void setCharges(String charges) {
		this.charges = charges;
	}
	public String getTotalCharges() {
		return totalCharges;
	}
	public void setTotalCharges(String totalCharges) {
		this.totalCharges = totalCharges;
	}
	public String getEndToEndId() {
		return endToEndId;
	}
	public void setEndToEndId(String endToEndId) {
		this.endToEndId = endToEndId;
	}
	public String getPaymentDetails() {
		return paymentDetails;
	}
	public void setPaymentDetails(String paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getRUB() {
		return RUB;
	}
	public void setRUB(String rUB) {
		RUB = rUB;
	}
	public String getExecutionDate() {
		return executionDate;
	}
	public void setExecutionDate(String executionDate) {
		this.executionDate = executionDate;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public String getTotalDebitAmount() {
		return totalDebitAmount;
	}
	public void setTotalDebitAmount(String totalDebitAmount) {
		this.totalDebitAmount = totalDebitAmount;
	}
	public String getAttachments() {
		return attachments;
	}
	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}
    
}
/*
{
    "authCodeNeeded": true,
    "payment": {
        "paymentId": "PmtId000001_1552388546711",
        "transactionTime": "1511779237",
        "status": {
            "code": "PNDG",
            "description": [
                "Payment in pending status"
            ],
            "refNumber": "CYP12345"
        },
        "debtor": {
            "bankId": "",
            "accountId": "351012345671"
        },
        "creditor": {
            "bankId": "",
            "accountId": "351012345672",
            "name": null,
            "address": null
        },
        "transactionAmount": {
            "amount": 3.55,
            "currency": "EUR",
            "currencyRate": "string"
        },
        "charges": null,
        "totalCharges": "1100.00",
        "endToEndId": "string",
        "paymentDetails": "test sandbox",
        "terminalId": "string",
        "branch": "",
        "RUB": null,
        "executionDate": "12/03/2019",
        "valueDate": "12/03/2019",
        "totalDebitAmount": null,
        "attachments": null
    }
}*/