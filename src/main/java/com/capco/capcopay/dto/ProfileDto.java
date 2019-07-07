package com.capco.capcopay.dto;

import java.util.Arrays;

import com.capco.capcopay.request.SelectedAccounts;

public class ProfileDto {
	
	 private SelectedAccounts[] selectedAccounts;
	 private String code;
	 private String email;

	    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

		public ProfileDto() {
	    }

		public SelectedAccounts[] getSelectedAccounts() {
			return selectedAccounts;
		}

		public void setSelectedAccounts(SelectedAccounts[] selectedAccounts) {
			this.selectedAccounts = selectedAccounts;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
		
		@Override
		public String toString() {
			return "ProfileDto [selectedAccounts=" + Arrays.toString(selectedAccounts) + ", code=" + code + "]";
		}

}
