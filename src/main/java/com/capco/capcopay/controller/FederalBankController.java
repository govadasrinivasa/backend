package com.capco.capcopay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capco.capcopay.federal.response.ViewAccountResponse;
import com.capco.capcopay.service.impl.FederalBankServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("capcopay/bank/federal")
@CrossOrigin(origins = "*")
@Api(value="Federal Bank Controller for Swagger")
public class FederalBankController {

	@Autowired
	FederalBankServiceImpl bankService;
	
	@PostMapping("/balance")
	@ApiOperation(value="Federal Bank Controller for balance")
	@ApiResponses(value= {
	@ApiResponse(code=200,message="Successful Retrieval",response=FederalBankController.class)
	})
	public ViewAccountResponse getBalanceEnquiry() {
		return bankService.viewAccountDetails();
//		return responseOAuth;
	}
}
