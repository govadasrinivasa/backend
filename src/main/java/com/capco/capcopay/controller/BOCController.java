package com.capco.capcopay.controller;

import java.io.IOException;

import javax.validation.Valid;

import com.capco.capcopay.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capco.capcopay.dto.ProfileDto;
import com.capco.capcopay.dto.SelectedAccountDetailsDto;
import com.capco.capcopay.dto.SubscribeDto;
import com.capco.capcopay.request.CreatePaymentRequest;
import com.capco.capcopay.service.impl.BOCServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("capcopay/bank/boc")
@CrossOrigin
@Api(value="BOC Controller for Swagger")
public class BOCController {

	@Autowired
	BOCServiceImpl bankApiService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BOCController.class);	
	
	@PostMapping("/subscription")
	@ApiOperation(value="Bank Controller for Cyprus OAuth")
	@ApiResponses(value= {
	@ApiResponse(code=200,message="Successful Retrieval",response=SubscriptionResponse.class)
	})
	public SubscriptionResponse getCitiAccessToken(@Valid @RequestBody SubscribeDto subscribeDto) throws IOException {
		SubscriptionResponse response = null;
        try {
        	response =  bankApiService.subscribe(subscribeDto);
        } catch (Exception e) {
            LOGGER.error("Masked error code: " + e.getMessage());           
        }
        return response;
	}
	
	/**
     * update profile to the application
     *
     * @param profileDto
     * @return
     * @throws Exception
     */
	@CrossOrigin
	@PostMapping("/updateProfile")
    @ApiOperation(value="Update Profile Controller for application")
	@ApiResponses(value= {
	@ApiResponse(code=200,message="Update Subscription with account numbers",response=UpdateSubscribeResponse.class)
	})
	public UpdateSubscribeResponse updateProfile(@Valid @RequestBody ProfileDto profileDto) throws IOException {
		UpdateSubscribeResponse response = null;
		try {
			response = bankApiService.updateSubscribe(profileDto);//(profileDto);

		} catch (Exception e) {
            LOGGER.error("Masked error code: " + e.getMessage());           
        }
        return response;
	}
	
	@PostMapping("/viewAllAcctDetails")
	@ApiOperation(value="Bank Controller for view details")
	@ApiResponses(value= {
	@ApiResponse(code=200,message="View all account details for given subscription Id",response=UserAccount.class)
	})
	public UserAccount[] getAllAccountDetails(@Valid @RequestBody SubscribeDto subscribeDto ) throws IOException {
		UserAccount[] accounts =  null;
		try {
			accounts =  bankApiService.viewAllAccountDetails(subscribeDto);
		} catch (Exception e) {
            LOGGER.error("Masked error code: " + e.getMessage());           
        }
		return accounts;
	}	
	
	
	@PostMapping("/viewAccountDetails")
	@ApiOperation(value="Bank Controller for view details")
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "View account details for given account numbers",response=UserAccount.class),
	    @ApiResponse(code = 500, message = "Internal server error")})
		
	public UserAccount[] getSelectedAccountDetails(@Valid @RequestBody SelectedAccountDetailsDto selectedDto) throws IOException {
		UserAccount[] accounts =  null;
		try {
			accounts =  bankApiService.viewSelectedAccountDetail(selectedDto);
		} catch (Exception e) {
            LOGGER.error("Masked error code: " + e.getMessage());           
        }
		return accounts;
	}
	
	@PostMapping("/createPayments")
	@ApiOperation(value="Bank Controller for create payments")
	@ApiResponses(value= {
	@ApiResponse(code=200,message="Make Payments",response=ApprovePaymentsResponse.class)
	})
	public ApprovePaymentsResponse createPayments(@Valid @RequestBody CreatePaymentRequest createPaymentRequest) throws IOException {
		try {
			return bankApiService.createPayment(createPaymentRequest);
		}catch(Exception e) {
			LOGGER.error("Error in creating payment: " + e.getMessage());   
		}
		return null;
 	}
@PostMapping("/userBankDetails")
	@ApiOperation(value="Bank Controller for User view details")
	@ApiResponses(value= {
	    @ApiResponse(code = 200, message = "Retrieve User Bank details",response=RegisteredBankNames.class),
	    @ApiResponse(code = 500, message = "Internal server error")})
		
	public RegisteredBankNames getUserBankDetails(@Valid @RequestBody SubscribeDto email) throws IOException {
	RegisteredBankNames accounts =  null;
		LOGGER.debug("Getting User Bank details for email: "+email.getEmail());
		try {
			accounts =  bankApiService.getUserBankList(email);
		} catch (Exception e) {
            LOGGER.error("Masked error code: " + e.getMessage());           
        }
		return accounts;
	}
	
	@PostMapping("/userAccountDetails")
	@ApiOperation(value="Bank Controller for User view details")
	@ApiResponses(value= {
	    @ApiResponse(code = 200, message = "Retrieve User Account details",response=UserAccountListResponse.class),
	    @ApiResponse(code = 500, message = "Internal server error")})
		
	public UserAccountListResponse getUserAccountDetails(@Valid @RequestBody SubscribeDto email) throws IOException {
		UserAccountListResponse accounts =  null;
		try {
			accounts =  bankApiService.getUserAccountDetails(email);
		} catch (Exception e) {
            LOGGER.error("Masked error code: " + e.getMessage());           
        }
		return accounts;
	}
	
	@PostMapping("/userBOCFeatures")
	@ApiOperation(value="Bank Controller for User BOC Feature list")
	@ApiResponses(value= {
	    @ApiResponse(code = 200, message = "Successful Retrieval",response=UserFeatureResponse.class),
	    @ApiResponse(code = 500, message = "Internal server error")})
		
	public UserFeatureResponse userBOCFeatures(@Valid @RequestBody SubscribeDto email) throws IOException {
		LOGGER.debug("Enter BOCController controller - user BOC Feature list ", this.getClass());
		UserFeatureResponse features =  null;
		try {
			features =  bankApiService.userBOCFeatures(email);
		} catch (Exception e) {
            LOGGER.error("Masked error code: " + e.getMessage());           
        }
		return features;
	}
}
