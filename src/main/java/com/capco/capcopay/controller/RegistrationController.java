package com.capco.capcopay.controller;

import javax.validation.Valid;

import com.capco.capcopay.response.RegistrationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capco.capcopay.dto.LoginDto;
import com.capco.capcopay.dto.RegistrationDto;
import com.capco.capcopay.entity.User;
import com.capco.capcopay.response.LoginResponse;
import com.capco.capcopay.service.RegistrationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/capcopay/registration")
@CrossOrigin(origins = "*")
@Api(value = "Registration")
public class RegistrationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
	
	private final RegistrationService registrationService;

    public RegistrationController(RegistrationService loginRegistrationService) {
        this.registrationService = loginRegistrationService;
    }
	
	/**
     * FIRST-TIME USER REGISTRATION | Registration
     *
     * @param registerDto
     * @return
     * @throws Exception\
     */
    @PostMapping("/register")
    @ApiOperation(value="Registration Controller for registration")
	@ApiResponses(value= {
	        @ApiResponse(code = 200,message="Successfully Registered",response=RegistrationResponse.class),
            @ApiResponse(code = 404,message = "User Not Found")
	})
    public RegistrationResponse registration(@Valid @RequestBody RegistrationDto registerDto) throws Exception {
        RegistrationResponse registrationResponse=new RegistrationResponse();
        try {
            registrationResponse=registrationService.registration(registerDto);
        } catch (Exception e) {
            LOGGER.error("Masked error code: " + e.getMessage());
        }
        return registrationResponse;
    }
    
    /**
     * Login to the application
     *
     * @param loginDto
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    @ApiOperation(value="Registration Controller for login")
	@ApiResponses(value= {
	        @ApiResponse(code = 200,message="Successful Login",response=LoginResponse.class),
            @ApiResponse(code = 401,message = "Login Failed!"),
            @ApiResponse(code = 400,message = "Bad Request"),
            @ApiResponse(code = 403,message = "Exception while processing")
	})
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try{
            LoginResponse loginResponse=registrationService.login(loginDto);
            if(loginResponse!=null){
                return new ResponseEntity<>(loginResponse, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Login Failed! May be user is not registered", HttpStatus.UNAUTHORIZED);
            }
        }catch (IllegalArgumentException e){
            LOGGER.debug(e.getMessage(),e);
            return new ResponseEntity<>("Bad Request!", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            LOGGER.debug(e.getMessage(),e);
            return new ResponseEntity<>("Exception occurred!", HttpStatus.FORBIDDEN);
        }

    	//return registrationService.login(loginDto);

	}
    
}
