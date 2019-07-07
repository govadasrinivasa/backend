//package com.capco.capcopay.controller;
//
//import java.io.IOException;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.capco.capcopay.dto.ProfileDto;
//import com.capco.capcopay.service.BankApiService;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//
//@RestController
//@RequestMapping("capcopay/bank")
//@CrossOrigin(origins = "*")
//@Api(value="Bank Controller for Swagger")
//public class BankController {
//
//	@Autowired
//	BankApiService bankApiService;
//	
//	@PostMapping("/subscribe")
//	@ApiOperation(value="Bank Controller for Subscription")
//	@ApiResponses(value= {
//	@ApiResponse(code=200,message="Successful Retrieval",response=BankController.class)
//	})
//	public String getsubscriptionId() throws IOException {
//		return bankApiService.subscribe();
//	}
//	
//	/**
//     * update profile to the application
//     *
//     * @param profileDto
//     * @return
//     * @throws Exception
//     */
//	@PatchMapping("/updateProfile")
//    @ApiOperation(value="Update Profile Controller for application")
//	@ApiResponses(value= {
//	@ApiResponse(code=200,message="Successful Retrieval",response=BankController.class)
//	})
//	public void updateProfile(@Valid @RequestBody ProfileDto profileDto) throws IOException {
//    	bankApiService.updateSubscribe(profileDto);//(profileDto);
//	}
//	
//	
//	@PostMapping("/viewDetails")
//	@ApiOperation(value="Bank Controller for view details")
//	@ApiResponses(value= {
//	@ApiResponse(code=200,message="Successful Retrieval",response=BankController.class)
//	})
//	public void getAccountDetails() throws IOException {
//		bankApiService.viewAccountDetails();
////		return responseOAuth;
//	}
//}
