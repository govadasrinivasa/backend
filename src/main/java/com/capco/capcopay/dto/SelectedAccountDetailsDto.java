package com.capco.capcopay.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SelectedAccountDetailsDto {
	private String selectedAccount;

	public String getSelectedAccount() {
		return selectedAccount;
	}

	@Override
	public String toString() {
		return "SelectedAccountDetailsDto [selectedAccount=" + selectedAccount + "]";
	}

	public void setSelectedAccount(String selectedAccount) {
		this.selectedAccount = selectedAccount;
	}

}
