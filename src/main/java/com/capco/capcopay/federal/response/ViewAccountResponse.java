package com.capco.capcopay.federal.response;

public class ViewAccountResponse {

	 private Account_statement_resp account_statement_resp;

	 public Account_statement_resp getAccount_statement_resp ()
	 {
		 return account_statement_resp;
	 }

	 public void setAccount_statement_resp (Account_statement_resp account_statement_resp)
	 {
		 this.account_statement_resp = account_statement_resp;
	 }

	 @Override
	 public String toString()
	 {
		 return "ClassPojo [account_statement_resp = "+account_statement_resp+"]";
	 }
}
