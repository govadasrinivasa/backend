package com.capco.capcopay.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class RegistrationDto {

	@NotBlank
    private String firstName;
	
	@NotBlank
	private String lastName;
	
	@NotBlank
	private String middleName;
	
	@NotBlank
	private String phoneNumber;
    
    @NotBlank
//    @Length(min = 8, max = 15)
    private String password;
    @NotBlank
    private String email;

    public RegistrationDto() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
    

}