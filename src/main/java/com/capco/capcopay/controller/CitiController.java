package com.capco.capcopay.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capco.capcopay.dto.SubscribeDto;
import com.capco.capcopay.response.SubscriptionResponse;
import com.capco.capcopay.service.CitiBankService;
import com.capco.capcopay.service.impl.CitiBankServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("capcopay/bank/citi")
@CrossOrigin(origins = "*")
@Api(value="Citi Bank Controller for Swagger")
public class CitiController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CitiController.class);
	
	@Autowired
	CitiBankServiceImpl citiBankService;
	
	@PostMapping("/authorize")
	@ApiOperation(value="Bank Controller for Citi Bank authorize")
	@ApiResponses(value= {
	@ApiResponse(code=200,message="Successful Retrieval",response=CitiController.class)
	})
	public SubscriptionResponse getAccessToken(@Valid @RequestBody SubscribeDto subscribeDto) throws IOException {
		LOGGER.debug("Enter BOCController controller - get access toekn ", this.getClass());
		SubscriptionResponse response = null;
        try {
        	citiBankService.getAccessToken();
        } catch (Exception e) {
            LOGGER.error("Masked error code: " + e.getMessage());           
        }
        return response;
	}
}
