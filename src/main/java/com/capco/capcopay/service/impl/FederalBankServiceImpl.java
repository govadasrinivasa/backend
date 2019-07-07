package com.capco.capcopay.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capco.capcopay.federal.request.Account_statement_req;
import com.capco.capcopay.federal.request.Body;
import com.capco.capcopay.federal.request.Header;
import com.capco.capcopay.federal.request.ViewAccountRequest;
import com.capco.capcopay.federal.response.ViewAccountResponse;
import com.capco.capcopay.service.FederalBankService;

@Service
public class FederalBankServiceImpl implements FederalBankService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FederalBankServiceImpl.class); 
	
	final RestTemplate restTemplate;
	final String client_id ="b5d764f0-b5db-457b-8aa8-c792eb02bed0";
	final String client_secret="yX2wL3uK5aY2cM0tM3sU5gW5hD8oJ3wQ4sC2vJ0qE7yN1xL3dU";
	
	public FederalBankServiceImpl(RestTemplate restTemplate) {
		this.restTemplate=restTemplate;
	}
	
	@Override
	public ViewAccountResponse viewAccountDetails() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accept", "capco");
		
		Header header = new Header();
		header.setUser_id("xyz");
		header.setPassword("xyz");

		Body body = new Body();
		body.setSender_cd("tuimoj");
		body.setAccount_num("5018412171087063");
		body.setDate("12/11/2051");
		body.setSeq_num("lihafive");
		
		Account_statement_req account_statement_req = new Account_statement_req();
		account_statement_req.setBody(body);
		account_statement_req.setHeader(header);
		
		ViewAccountRequest viewAccountRequest = new ViewAccountRequest();
		viewAccountRequest.setAccount_statement_req(account_statement_req);
		
		ResponseEntity<ViewAccountResponse> response = null;
  
		try {       	
			HttpEntity<ViewAccountRequest> entity = new HttpEntity<ViewAccountRequest>(viewAccountRequest, headers);
			response = restTemplate.exchange("https://sandgateway.federalbank.co.in/paylite/accountStatement", HttpMethod.POST, entity, ViewAccountResponse.class);
			System.out.println("Result - status ("+ response.getStatusCode() + ") has body: " + response.hasBody());      
      } catch (Exception e) {
   	   e.getMessage();
          e.printStackTrace();
      } 
		return response.getBody();
	}
}
