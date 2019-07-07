package com.capco.capcopay.service;

import com.capco.capcopay.dto.LoginDto;
import com.capco.capcopay.dto.RegistrationDto;
import com.capco.capcopay.response.LoginResponse;
import com.capco.capcopay.response.RegistrationResponse;

public interface RegistrationService {

	public RegistrationResponse registration(RegistrationDto registerDto) throws Exception;
	
    public LoginResponse login(LoginDto loginDto);
}
