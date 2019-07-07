package com.capco.capcopay.response;

public class CreatePaymentResponse {

	private String authCodeNeeded ;
	private Payment payment;
	public String getAuthCodeNeeded() {
		return authCodeNeeded;
	}
	public void setAuthCodeNeeded(String authCodeNeeded) {
		this.authCodeNeeded = authCodeNeeded;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
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