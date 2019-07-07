package com.capco.capcopay.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.capco.capcopay.response.RegistrationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.capco.capcopay.dto.LoginDto;
import com.capco.capcopay.dto.RegistrationDto;
import com.capco.capcopay.entity.Subscription;
import com.capco.capcopay.entity.User;
import com.capco.capcopay.repository.UserRepository;
import com.capco.capcopay.response.LoginResponse;
import com.capco.capcopay.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationServiceImpl.class);

	private final UserRepository userRepository;
	
	public RegistrationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	@Override
	public RegistrationResponse registration(RegistrationDto registerDto) throws Exception {
		LOGGER.debug("Registering User: "+registerDto.getEmail());
		RegistrationResponse registrationResponse=new RegistrationResponse();
		checkExistingUser(registerDto.getEmail());
        User user = new User(registerDto.getEmail());

        user.setPassword(registerDto.getPassword());
        user.setFirstName(registerDto.getFirstName());
        user.setMiddleName(registerDto.getMiddleName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setPhoneNumber(registerDto.getPhoneNumber());   
        user.setLastLoginDate(new Date());
        User userSaved=userRepository.save(user);
        if(userSaved==null){
			LOGGER.debug("Registration Unsuccessful");
			registrationResponse.setMessage("Registration Unsuccessful");
			registrationResponse.setStatusCode("500");
		}else{
			LOGGER.debug("Registration Successful");
			registrationResponse.setMessage("Registration Successful");
			registrationResponse.setStatusCode("200");
		}

        return registrationResponse;
	}

	@Override
	public LoginResponse login(LoginDto loginDto) {
		LOGGER.debug("Logging in User: "+loginDto.getEmail());
		LoginResponse response = null;
        User user = userRepository.findByEmail(loginDto.getEmail());
        if(user == null) {
			LOGGER.debug("User Not Found");
        	return null;
        }else {
			LOGGER.debug("User logged in successfully");
        	response = new LoginResponse();
        	System.out.println(user);
        	response.setEmail(user.getEmail());
        	response.setFirstName(user.getFirstName());
        	response.setLastName(user.getLastName());
        	response.setMiddleName(user.getMiddleName());
        	response.setPassword(user.getPassword());
        	response.setPhoneNumber(user.getPhoneNumber());
        	response.setSubscriptionFlag(user.isSubscriptionFlag());
        	if(user.isSubscriptionFlag()) {
        		//String[] bankNames = new String[10];
        		List<String> bNames = new ArrayList<String>();
        		List<Subscription> sub = user.getSubscription();
        		for(Subscription j : sub) {
        			bNames.add(j.getBankName());
        		}
        		String[] bankNames = new String[bNames.size()];
        		bankNames = bNames.toArray(bankNames);
        		response.setBankNames(bankNames);
        	}else {
        		response.setBankNames(new String[0]);
        	}
        	
        }
        return response;
	}
	
    private void checkExistingUser(String email) throws Exception {
    	User user = userRepository.findByEmail(email);
        if (user != null) {
            throw new Exception("User already Exists");
        }
    }
}
