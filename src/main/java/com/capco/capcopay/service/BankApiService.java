package com.capco.capcopay.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.capco.capcopay.dto.ProfileDto;
import com.capco.capcopay.entity.UserAccounts;
import com.capco.capcopay.repository.AccountRepository;
import com.capco.capcopay.repository.UserRepository;
import com.capco.capcopay.request.Accounts;
import com.capco.capcopay.request.Payments;
import com.capco.capcopay.request.SelectedAccounts;
import com.capco.capcopay.request.SubscribeRequest;
import com.capco.capcopay.request.UpdateSubscribeRequest;
import com.capco.capcopay.response.ResponseOAuth;
import com.capco.capcopay.response.SubscribeResponse;

@Service
public class BankApiService {

	final RestTemplate restTemplate;
	private final UserRepository userRepository;
	private final AccountRepository accountRepo;
	final String client_id ="da80dce3-654a-4173-b3bc-18ee62af78c7";
	final String client_secret="lD4yD8bT1pR4bO6pJ1iB6iR2gR8pK0oG6bI3bR0fV7rC5yD8fK";
	String subscriptionId;
	
	final SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
	final String journeyId =  "capco";
	final String app_name = "CapcoPaygrant_type_11";
	final String originUserId  = "srinivas";
	final String tppId = "singpaymentdata";
	
	final String viewAccounts_URL = "https://sandbox-apis.bankofcyprus.com/df-boc-org-sb/sb/psd2/v1/accounts/";
	public BankApiService(RestTemplate restTemplate,UserRepository userRepository,AccountRepository accountRepo) {
		this.restTemplate=restTemplate;
		this.userRepository=userRepository;
		this.accountRepo=accountRepo;
	}
	
	public ResponseOAuth getAccessToken() {
		
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("client_id", client_id);
        map.add("client_secret", client_secret);
        map.add("grant_type", "client_credentials");
        map.add("scope", "TPPOAuth2Security");
        
        ResponseOAuth body = null;
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        
        try {
        	ResponseEntity<ResponseOAuth> response = restTemplate.postForEntity("https://sandbox-apis.bankofcyprus.com/df-boc-org-sb/sb/psd2/oauth2/token", request , ResponseOAuth.class);
        	body = response.getBody();
        	System.out.println(body);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        return body;
	}
	
	public String subscribe() {
		
		String tstamp = sdf.format(timestamp);
		
		SubscribeRequest subscribeRequest = new SubscribeRequest();
		subscribeRequest.setPayments(getPaymentsForSubscription());
		subscribeRequest.setAccounts(getAccountsForSubscription());
		
        ResponseOAuth responseOAuth = getAccessToken();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("journeyId", journeyId);
        headers.add("app_name", "CapcoPay1");
        headers.add("originUserId", "srinivas");
        headers.add("timeStamp", tstamp);
        headers.add("tppId", "singpaymentdata");
        headers.add("Authorization", "Bearer "+responseOAuth.getAccess_token());

        HttpEntity<SubscribeRequest> request = new HttpEntity<SubscribeRequest>(subscribeRequest, headers);
        
        try {
        	ResponseEntity<SubscribeResponse> response = restTemplate.postForEntity("https://sandbox-apis.bankofcyprus.com/df-boc-org-sb/sb/psd2/v1/subscriptions?client_id=da80dce3-654a-4173-b3bc-18ee62af78c7&client_secret=lD4yD8bT1pR4bO6pJ1iB6iR2gR8pK0oG6bI3bR0fV7rC5yD8fK", request , SubscribeResponse.class );
        	subscriptionId = response.getBody().getSubscriptionId();
        	System.out.println(subscriptionId);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return subscriptionId;
	}
	
	public ResponseOAuth getSecondAccessToken(String code) {
		
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("client_id", "da80dce3-654a-4173-b3bc-18ee62af78c7");
        map.add("client_secret", client_secret);
        map.add("grant_type", "authorization_code");
        map.add("scope", "UserOAuth2Security");
        map.add("code", code);
        
        ResponseOAuth body = null;
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        
        try {
        	ResponseEntity<ResponseOAuth> response = restTemplate.postForEntity("https://sandbox-apis.bankofcyprus.com/df-boc-org-sb/sb/psd2/oauth2/token", request , ResponseOAuth.class);
        	body = response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        return body;
	}
	
	public void updateSubscribe(ProfileDto profileDto) {
		
		String tstamp = sdf.format(timestamp);
		
		UpdateSubscribeRequest subscribeRequest = new UpdateSubscribeRequest();
		subscribeRequest.setAccounts(getAccountsForSubscription());
		subscribeRequest.setPayments(getPaymentsForSubscription());
		subscribeRequest.setSelectedAccounts(profileDto.getSelectedAccounts());
		
        ResponseOAuth responseOAuth = getSecondAccessToken(profileDto.getCode());
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("journeyId", journeyId);
        headers.add("APIm-Debug-Trans-Id", "true");
        headers.add("originUserId", originUserId);
        headers.add("timeStamp", tstamp);
        headers.add("tppId", tppId);
        headers.add("Authorization", "Bearer "+responseOAuth.getAccess_token());

        HttpEntity<UpdateSubscribeRequest> request = new HttpEntity<UpdateSubscribeRequest>(subscribeRequest, headers);
        
        try {
        	 String response = restTemplate.patchForObject("https://sandbox-apis.bankofcyprus.com/df-boc-org-sb/sb/psd2/v1/subscriptions/"+subscriptionId+"?client_id="+client_id+"&client_secret="+client_secret,request ,String.class);
        	 if(null != profileDto.getSelectedAccounts() && profileDto.getSelectedAccounts().length >0){
            	for(SelectedAccounts account : profileDto.getSelectedAccounts()) {
	     	    	UserAccounts accounts = new UserAccounts();
	//     	       	accounts.setSubscription(subscription);
	     	       	accounts.setLastSubscriptionDate(new Date());
	     	       	accounts.setUser(userRepository.findByEmail(profileDto.getEmail()));
	     	       	accounts.setAccountId(account.getAccountId());
	     	       	accountRepo.save(accounts);
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
	}
	
		
	public void viewAccountDetails() {
		System.out.println("============Method - viewAccountDetails ======started=============");
		String tstamp = sdf.format(timestamp);
		ResponseOAuth responseOAuth = getAccessToken();


		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("journeyId", "capco");
		headers.add("subscriptionId", subscriptionId);
		headers.add("originUserId", originUserId);
		headers.add("timeStamp", tstamp);
		headers.add("tppId", tppId);
		headers.add("Authorization",  "Bearer "+responseOAuth.getAccess_token());
  
		try {       	
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//			ResponseEntity<String> response = restTemplate.exchange("https://sandbox-apis.bankofcyprus.com/df-boc-org-sb/sb/psd2/v1/accounts?client_id=da80dce3-654a-4173-b3bc-18ee62af78c7&client_secret=lD4yD8bT1pR4bO6pJ1iB6iR2gR8pK0oG6bI3bR0fV7rC5yD8fK", HttpMethod.GET, entity, String.class);
			ResponseEntity<String> response = restTemplate.exchange(viewAccounts_URL+"/351012345671/balance?client_id="+client_id+"&client_secret="+client_secret, HttpMethod.GET, entity, String.class);
			System.out.println("Result - status ("+ response.getStatusCode() + ") has body: " + response.hasBody());      
       	
      	 	System.out.println("============Method - viewAccountDetails ==================="+response);
      	 
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		} 
	}
	
	private Accounts getAccountsForSubscription() {
		Accounts accounts = new Accounts();
		accounts.setTransactionHistory("true");
		accounts.setBalance("true");
		accounts.setDetails("true");
		accounts.setCheckFundsAvailability("true");
		return accounts;
	}
	
	private Payments getPaymentsForSubscription() {
		Payments payments = new Payments();
		payments.setLimit("8.64181767");
		payments.setCurrency("EUR");
		payments.setAmount("93.21948702");
		return payments;
	}
}
