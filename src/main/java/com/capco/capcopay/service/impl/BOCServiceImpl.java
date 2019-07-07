package com.capco.capcopay.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capco.capcopay.controller.BOCController;
import com.capco.capcopay.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.capco.capcopay.dto.LoginDto;
import com.capco.capcopay.dto.ProfileDto;
import com.capco.capcopay.dto.SelectedAccountDetailsDto;
import com.capco.capcopay.dto.SubscribeDto;
import com.capco.capcopay.entity.Bank;
import com.capco.capcopay.entity.BankServices;
import com.capco.capcopay.entity.Subscription;
import com.capco.capcopay.entity.User;
import com.capco.capcopay.entity.UserAccounts;
import com.capco.capcopay.repository.AccountRepository;
import com.capco.capcopay.repository.BankRepository;
import com.capco.capcopay.repository.BankServiceRepository;
import com.capco.capcopay.repository.PaymentsRepository;
import com.capco.capcopay.repository.SubscriptionRepository;
import com.capco.capcopay.repository.UserRepository;
import com.capco.capcopay.request.Accounts;
import com.capco.capcopay.request.ApprovePaymentRequest;
import com.capco.capcopay.request.CreatePaymentRequest;
import com.capco.capcopay.request.Payments;
import com.capco.capcopay.request.SubscribeRequest;
import com.capco.capcopay.request.UpdateSubscribeRequest;
import com.capco.capcopay.service.BOCService;


@Service
public class BOCServiceImpl implements BOCService {

		private static final Logger LOGGER = LoggerFactory.getLogger(BOCServiceImpl.class);
		final RestTemplate restTemplate;
		//TODO- Client_Id and Client_secret for Srinivas Cyprus bank developer portal
		final String client_id ="da80dce3-654a-4173-b3bc-18ee62af78c7";
		final String  client_secret = "lD4yD8bT1pR4bO6pJ1iB6iR2gR8pK0oG6bI3bR0fV7rC5yD8fK";
		String subscriptionId = "Subid000001-1552295310124";
		
		final String grant_type = "client_credentials";
		final String scope =  "TPPOAuth2Security";
		
		final String grant_type_1 ="authorization_code";
		final String scope_1 = "UserOAuth2Security";
	    
		final String journeyId =  "capco";
		final String app_name = "CapcoPaygrant_type_11";
		final String originUserId  = "srinivas";
		final String tppId = "singpaymentdata";
		
		
		final String OAuth_URL = "https://sandbox-apis.bankofcyprus.com/df-boc-org-sb/sb/psd2/oauth2/token";
		final String subscribe_URL = "https://sandbox-apis.bankofcyprus.com/df-boc-org-sb/sb/psd2/v1/subscriptions";
		//final String updateSubscribe_URL = "https://sandbox-apis.bankofcyprus.com/df-boc-org-sb/sb/psd2/v1/subscriptions/";
		final String viewAccounts_URL = "https://sandbox-apis.bankofcyprus.com/df-boc-org-sb/sb/psd2/v1/accounts";
		final String createSignature_URL = "https://sandbox-apis.bankofcyprus.com/df-boc-org-sb/sb/jwssignverifyapi/sign";

		
		final SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		private final SubscriptionRepository subRepo;
		private final AccountRepository accountRepo;
		private final UserRepository userRepository;
		private final PaymentsRepository paymentsRepository;
		private final BankServiceRepository bankServiceRepo;
		private final  BankRepository bankRepository;
	
		public BOCServiceImpl(RestTemplate restTemplate,UserRepository userRepository,
				AccountRepository accountRepo,SubscriptionRepository subRepo, PaymentsRepository paymentsRepository, 
				BankServiceRepository bankServiceRepo, BankRepository bankRepository) {
			this.restTemplate=restTemplate;
			this.subRepo = subRepo;
			this.accountRepo = accountRepo;
			this.userRepository = userRepository;
			this.paymentsRepository=paymentsRepository;
			this.bankServiceRepo=bankServiceRepo;
			this.bankRepository = bankRepository;
		}
		
		public ResponseOAuth getAccessToken() { 
			
			MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
	        map.add("client_id", client_id);
	        map.add("client_secret", client_secret);
	        map.add("grant_type", grant_type);
	        map.add("scope", scope);
	        
	        ResponseOAuth body = null;
	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
	        
	        try {
	        	ResponseEntity<ResponseOAuth> response = restTemplate.postForEntity(OAuth_URL, request , ResponseOAuth.class);
	        	body = response.getBody();
	        	LOGGER.debug("First access token: "+body.getAccess_token());
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	        
	        return body;
		}
		
		public Payments getPaymentsForSubscription() {
			Payments payments = new Payments();
			payments.setLimit("8.64181767");
			payments.setCurrency("EUR");
			payments.setAmount("93.21948702");
			return payments;
		}
		
		public Accounts getAccountsForSubscription() {
			Accounts accounts = new Accounts();
			accounts.setTransactionHistory("true");
			accounts.setBalance("true");
			accounts.setDetails("true");
			accounts.setCheckFundsAvailability("true");
			return accounts;
		}
		
		public SubscriptionResponse subscribe(SubscribeDto subscribeDto) { 
			LOGGER.debug("User EmailId: "+subscribeDto.getEmail());
			SubscriptionResponse response2 = null;
			String tstamp = sdf.format(timestamp);
			SubscribeRequest subscribeRequest = new SubscribeRequest();		
			subscribeRequest.setPayments(getPaymentsForSubscription());
			subscribeRequest.setAccounts(getAccountsForSubscription());		        
	        ResponseOAuth responseOAuth = getAccessToken();        
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.add("journeyId", journeyId);
	        headers.add("app_name", app_name);
	        headers.add("originUserId", originUserId);
	        headers.add("timeStamp", tstamp);
	        headers.add("tppId", tppId);
	        headers.add("Authorization", "Bearer "+responseOAuth.getAccess_token());

	        HttpEntity<SubscribeRequest> request = new HttpEntity<SubscribeRequest>(subscribeRequest, headers);
	        User user = null;
	        try {
	        	response2 = new SubscriptionResponse();
	        	ResponseEntity<SubscribeResponse> response = restTemplate.postForEntity(subscribe_URL+"?client_id="+client_id+"&client_secret="+client_secret, request , SubscribeResponse.class );
	        	subscriptionId = response.getBody().getSubscriptionId();
	        	LOGGER.debug("SubscriptionId from Bank: "+subscriptionId);
	        	Subscription entity = new Subscription();
	        	entity.setLastSubscriptionDate(new Date());
	        	entity.setSubscriptionId(subscriptionId);
	        	entity.setBankName("Bank of Cyprus");
	        	user = userRepository.findByEmail(subscribeDto.getEmail());
	        	entity.setUser(user);
	        	subRepo.save(entity);
	        	//userRepository.save(user);
	        	response2.setSubscriptionId(subscriptionId);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	        return response2;
		}


		public ResponseOAuth getSecondAccessToken(ProfileDto profileDtoS) {  //input - client_id, client_secret, grant_type, scope, code
			String code = profileDtoS.getCode();
			MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
	        map.add("client_id", client_id);
	        map.add("client_secret", client_secret);
	        map.add("grant_type", grant_type_1);
	        map.add("scope", scope_1);
	        map.add("code", code); //get input from FE
	        
	        ResponseOAuth body = null;	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);	        
	        try {
	        	ResponseEntity<ResponseOAuth> response = restTemplate.postForEntity(OAuth_URL, request , ResponseOAuth.class);
	        	body = response.getBody();
	        	LOGGER.debug("Second access token: "+body.getAccess_token());
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 	        
	        return body;
		}
		
		public UpdateSubscribeResponse updateSubscribe(ProfileDto profileDtoS) {
			LOGGER.debug("Updating Subscription");
			String tstamp = sdf.format(timestamp);
			UpdateSubscribeRequest subscribeRequest = new UpdateSubscribeRequest();		
			subscribeRequest.setAccounts(getAccountsForSubscription());
			subscribeRequest.setPayments(getPaymentsForSubscription());
			subscribeRequest.setSelectedAccounts(profileDtoS.getSelectedAccounts());//getSelectedAccounts());	//	profileDtoS	        
	        ResponseOAuth responseOAuth = getSecondAccessToken(profileDtoS);	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);        
	        headers.add("journeyId", journeyId);
	        headers.add("APIm-Debug-Trans-Id", "true");
	        headers.add("originUserId", originUserId);
	        headers.add("timeStamp", tstamp);
	        headers.add("tppId", tppId);
	        headers.add("Authorization", "Bearer "+responseOAuth.getAccess_token());
	        
	        UpdateSubscribeResponse  response = null ;

	        HttpEntity<UpdateSubscribeRequest> request = new HttpEntity<UpdateSubscribeRequest>(subscribeRequest, headers);
	      //  Subscription  subscription = null;
	        try {
				LOGGER.debug("EmailId of the user: "+profileDtoS.getEmail());
	        	Subscription  subscription = subRepo.findByUserEmail(profileDtoS.getEmail());
	        	LOGGER.debug("SubscriptionId from DB: "+subscription.getSubscriptionId());
	        	response = restTemplate.patchForObject(subscribe_URL+"/"+subscription.getSubscriptionId()+"?client_id="+client_id+"&client_secret="+client_secret, request , UpdateSubscribeResponse.class);
	       	 	LOGGER.debug("Subscription Update Response: "+response.toString());
	       	
	       	if(null != response.getSelectedAccounts() && response.getSelectedAccounts().length >0){
	       		User user = null;
	       		if(userRepository.findByEmail(profileDtoS.getEmail()) != null) {
	       			user =userRepository.findByEmail(profileDtoS.getEmail());
	           	}
	       		for(com.capco.capcopay.response.SelectedAccounts account : response.getSelectedAccounts()) {
		       		UserAccounts accounts = new UserAccounts();	       		
		           	accounts.setSubscription(subscription);
		           	accounts.setLastSubscriptionDate(new Date());	           	
		           	accounts.setUser(user);
		           	accounts.setAccountId(account.getAccountId());
		           	accountRepo.save(accounts);
		           
	       		}
	       		Bank bank = bankRepository.findByBankName("Bank of Cyprus");
	       		//bankServiceRepo.
	       		com.capco.capcopay.response.Accounts accounts = response.getAccounts();
	       		BankServices bankService = new BankServices();
	       		bankService.setBankServiceName("Balance");
	       		bankService.setBank(bank);
	       		bankService.setUser(user);
	       		bankService.setServiceSubscribed(Boolean.valueOf(accounts.getBalance()));
	       		BankServices bankService1 = new BankServices();
	       		bankService1.setBankServiceName("CheckFundsAvailability");
	       		bankService1.setBank(bank);
	       		bankService1.setUser(user);
	       		bankService1.setServiceSubscribed(Boolean.valueOf(accounts.getCheckFundsAvailability()));
	       		BankServices bankService2 = new BankServices();
	       		bankService2.setBankServiceName("Details");
	       		bankService2.setBank(bank);
	       		bankService2.setUser(user);
	       		bankService2.setServiceSubscribed(Boolean.valueOf(accounts.getDetails()));
	       		BankServices bankService3 = new BankServices();
	       		bankService3.setBankServiceName("TransactionHistory");
	       		bankService3.setBank(bank);
	       		bankService3.setUser(user);
	       		bankService3.setServiceSubscribed(Boolean.valueOf(accounts.getTransactionHistory()));
	       		
	       		bankServiceRepo.save(bankService);
	       		bankServiceRepo.save(bankService1);
	       		bankServiceRepo.save(bankService2);
	       		bankServiceRepo.save(bankService3);
	       		
	       		user.setSubscriptionFlag(true);
	       		userRepository.save(user);
	       	}
	        } catch (Exception e) {
	            e.printStackTrace();
	            response = null;
	        }
	        return response;
		}

		
		//All accounts
		@Override
		public com.capco.capcopay.response.UserAccount[] viewAllAccountDetails(SubscribeDto subscribeDto) { //input - subscriptionId
			 LOGGER.debug("============Method - viewAccountDetails ======started============="+subscriptionId);
			 String tstamp = sdf.format(timestamp);
			 ResponseOAuth responseOAuth = getAccessToken();

	       HttpHeaders headers = new HttpHeaders();
	       
	       com.capco.capcopay.response.UserAccount[] accounts = null;
	       try {       	
	        	
	    		Subscription subscription = subRepo.findByUserEmail(subscribeDto.getEmail());
	    		if(null != subscription) {
	    			headers.add("subscriptionId", subscription.getSubscriptionId());
	    		}else {
	    			headers.add("subscriptionId", null);
	    		}
	    		headers.setContentType(MediaType.APPLICATION_JSON);
	 	       headers.add("journeyId", journeyId);
	 	       
	 	       headers.add("originUserId", originUserId);
	 	       headers.add("timeStamp", tstamp);
	 	       headers.add("tppId", tppId);       
	 	       headers.add("Authorization",  "Bearer "+responseOAuth.getAccess_token());//"AAIkZGE4MGRjZTMtNjU0YS00MTczLWIzYmMtMThlZTYyYWY3OGM38mUndJMHbhmKZWaeFtLHUIBpkIHwu9SV1k4CH9Pj8nRCPr1_C2R3lBPfAJcHve0ioyD2Oaex9BB_PLWi0s7uv8BofMq-pnhTTa6pxfzNX3zRsIx6yB5wnSgED2inBalXetfIsXdPOdALmBFg3ivtLw"); // first access token
	 	   
	    	   
	    	   HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	    	   ResponseEntity<com.capco.capcopay.response.UserAccount[]> response = restTemplate.exchange(viewAccounts_URL+"?client_id="+client_id+"&client_secret="+client_secret, HttpMethod.GET, entity, com.capco.capcopay.response.UserAccount[].class);
	           LOGGER.debug("Result - status ("+ response.getStatusCode() + ") has body: " + response.hasBody());
	        
	       	accounts = response.getBody();
	       } catch (Exception e) {
	    	   e.getMessage();
	           e.printStackTrace();
	       } 
	       return accounts;
		}
		

		@Override
		public com.capco.capcopay.response.UserAccount[] viewSelectedAccountDetail(SelectedAccountDetailsDto selectedAccounts) { //input - subscriptionId
			 LOGGER.debug("============Method - viewAccountDetails ======started============="+subscriptionId);
			 String tstamp = sdf.format(timestamp);
			 ResponseOAuth responseOAuth = getAccessToken();

	       HttpHeaders headers = new HttpHeaders();
	       headers.setContentType(MediaType.APPLICATION_JSON);
	       headers.add("journeyId", journeyId);
	       headers.add("subscriptionId", subscriptionId);
	       headers.add("originUserId", originUserId);
	       headers.add("timeStamp", tstamp);
	       headers.add("tppId", tppId);       
	       headers.add("Authorization",  "Bearer "+responseOAuth.getAccess_token());//"AAIkZGE4MGRjZTMtNjU0YS00MTczLWIzYmMtMThlZTYyYWY3OGM38mUndJMHbhmKZWaeFtLHUIBpkIHwu9SV1k4CH9Pj8nRCPr1_C2R3lBPfAJcHve0ioyD2Oaex9BB_PLWi0s7uv8BofMq-pnhTTa6pxfzNX3zRsIx6yB5wnSgED2inBalXetfIsXdPOdALmBFg3ivtLw"); // first access token
	   
	       com.capco.capcopay.response.UserAccount[] accounts = null;
	       try {       	
	        	   
	    	   HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	    	   ResponseEntity<com.capco.capcopay.response.UserAccount[]> response = restTemplate.exchange(viewAccounts_URL+"/"+selectedAccounts.getSelectedAccount()+"?client_id="+client_id+"&client_secret="+client_secret, HttpMethod.GET, entity, com.capco.capcopay.response.UserAccount[].class);
	           LOGGER.debug("Result - status ("+ response.getStatusCode() + ") has body: " + response.hasBody());
	        	
	       	 //	LOGGER.debug("============Method - viewAccountDetails ==================="+response.getBody());
	       	 	
		       	//LOGGER.debug(response);
		       	accounts = response.getBody();
		       } catch (Exception e) {
		    	   e.getMessage();
		           e.printStackTrace();
		       } 
		       return accounts;
			}
		
		
		@Override
		public RegisteredBankNames getUserBankList(SubscribeDto email) {
			RegisteredBankNames registeredBankNames=new RegisteredBankNames();
			BankNamesResponse[] response;
			int count=0;
	        User user = userRepository.findByEmail(email.getEmail());
	        if(user == null) {
	        	return null;
	        }else { 

	        	LOGGER.debug("User "+user);
	        	if(user.isSubscriptionFlag()) {
	        		//String[] bankNames = new String[10];
	        		//List<String> bNames = new ArrayList<String>();
	        		List<Subscription> sub = user.getSubscription();
					response = new BankNamesResponse[sub.size()];
	        		for(Subscription j : sub) {
	        			BankNamesResponse res=new BankNamesResponse();
	        			res.setBankName(j.getBankName());
	        			response[count]=res;
	        			count++;
	        		}
	        		//String[] bankNames = new String[bNames.size()];
	        		//bankNames = bNames.toArray(bankNames);
	        		//response.setBankNames(bankNames);
	        	}else {
					response = new BankNamesResponse[0];
	        		//response.setBankNames(new String[0]);
	        	}
	        	
	        }
	        registeredBankNames.setBankNamesResponses(response);
	        return registeredBankNames;
		}
		
		@Override
		public UserAccountListResponse getUserAccountDetails(SubscribeDto email) {
			UserAccountListResponse response = new UserAccountListResponse();
	        User user = userRepository.findByEmail(email.getEmail());
	        if(user == null) {
	        	response.setAccountNumberList(new BankAccountListResponse[0]);
    			response.setUserSubscribedMsg("User is not present.");
	        	return response;
	        }else {
	        	LOGGER.debug("User : "+user);
	        	if(user.isSubscriptionFlag()) {
	        		List<String> bNames = new ArrayList<String>();     		
	        		List<Subscription> sub = user.getSubscription();
	        		if(sub != null && sub.size() > 0 ) {
		        		BankAccountListResponse[] accResponses =  new BankAccountListResponse[sub.size()];
		        		for(int v =0; v <sub.size();v++) {
		        			bNames.add(sub.get(v).getBankName());
		        			List<UserAccounts> accounts = accountRepo.findBySubscriptionId(sub.get(v).getId());
		        			LOGGER.debug("Accounts: "+accounts);
		        			if(accounts != null && accounts.size() > 0 ) {
		        				String[] accountsList = new String[accounts.size()];
		        				BankAccountListResponse bankAccountListResponse = new BankAccountListResponse();	        					
		        				for(int k =0 ;k<accounts.size();k++) {
		        					accountsList[k] = accounts.get(k).getAccountId();
		        				}
		        				bankAccountListResponse.setAccountId(accountsList);
		        				bankAccountListResponse.setBankName(sub.get(v).getBankName());
		        				accResponses[v] = bankAccountListResponse;
		        			}
		        			response.setAccountNumberList(accResponses);
		        			response.setUserSubscribedMsg("User is Suscribed for "+accResponses.length+ " banks.");
		        		}
	        		}
	        	}else {
	        		response.setAccountNumberList(new BankAccountListResponse[0]);
        			response.setUserSubscribedMsg("User is not Suscribed for any banks.");
	        	}
	        }
	        return response;
		}
		
		@Override
		public UserFeatureResponse userBOCFeatures(SubscribeDto email) {
			UserFeatureResponse response = new UserFeatureResponse();
	        User user = userRepository.findByEmail(email.getEmail());
	        if(user == null) {
    			response.setServiceName(new String[0]);
	        	return response;
	        }else {
	        	System.out.println(user);
	        	if(user.isSubscriptionFlag()) {
	        		List<BankServices> bankServices = bankServiceRepo.findByBankIdAndUserId(Long.valueOf("1"), user.getId());
	        		String[] listOfServices = new String[bankServices.size()];
	        		if(bankServices.size()>0) {
	        			for(int k=0;k<bankServices.size();k++) {
	        				listOfServices[k] = bankServices.get(k).getBankServiceName();
	        			}
	        			response.setServiceName(listOfServices);
	        		}	        		
	        	}    
	        }
	        return response;
		}
	
		@Override
		public ApprovePaymentsResponse createPayment(CreatePaymentRequest createPaymentRequest) {
			Payment paymentObj = new Payment();
			ApprovePaymentsResponse approvePaymentsResponse = new ApprovePaymentsResponse();

			try {
				String response = createSignatureForPayments(createPaymentRequest);
				boolean result= checkFundAvailability(createPaymentRequest.getDebtAccountId(),createPaymentRequest.getAmount());
				if(result) {
					paymentObj= createPaymentAftersignature(response);
					ApprovePaymentRequest approvePaymentRequest = new ApprovePaymentRequest();
					approvePaymentRequest.setPaymentId(paymentObj.getPaymentId());
					approvePaymentRequest.setTransactionTime(paymentObj.getTransactionTime());
					approvePaymentsResponse= approvePayment(approvePaymentRequest);
					com.capco.capcopay.entity.Payments payments = new com.capco.capcopay.entity.Payments();
					payments.setAmount(createPaymentRequest.getAmount());
					payments.setCreditAccountId(createPaymentRequest.getCreditAccountId());
					payments.setDebitAccountId(createPaymentRequest.getDebtAccountId());
					payments.setRefNumber(approvePaymentsResponse.getRefNumber());
					payments.setTransactionCode(approvePaymentsResponse.getCode());
					payments.setTransactionDateTime(new Date());
					paymentsRepository.save(payments);
					approvePaymentsResponse.setStatusCode("Success");
					return approvePaymentsResponse;
					}else {
						approvePaymentsResponse.setMessage("No Funds to transfer");
						approvePaymentsResponse.setStatusCode("Success");
						}
				} catch (Exception e) {
					e.getMessage();
					e.printStackTrace();
					}
			return approvePaymentsResponse;

		}

		private boolean checkFundAvailability(String accountId, Double amount) {
			LOGGER.debug("============Method - checkFundAvailability ======started=============");
			String tstamp = sdf.format(timestamp);
			ResponseOAuth responseOAuth = getAccessToken();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("tppId", "singpaymentdata");
			headers.add("journeyId", "journeyId");
			headers.add("Authorization", "Bearer " + responseOAuth.getAccess_token());
			headers.add("originUserId", "srinivas");
			headers.add("timeStamp", tstamp);
			headers.add("subscriptionId", subscriptionId);
			ResponseEntity<String> response = null;
			try {

				String requestBody ="{ \r\n" + 
						"	\"bankId\": \"abc\",\r\n" + 
						"		\"accountId\": \""+accountId+"\",\r\n" + 
						"		\r\n" + 
						"		\"transaction\": {\r\n" + 
						"		\"amount\": "+amount+",\r\n" + 
						"		\"currency\": \"EUR\",\r\n" + 
						"		\"currencyRate\": \"60\"\r\n" + 
						"	}\r\n" + 
						"}";
				HttpEntity<String> request = new HttpEntity<String>(requestBody, headers);

				try {
				  response = restTemplate.postForEntity("https://sandbox-apis.bankofcyprus.com/df-boc-org-sb/sb/psd2/v1/payments/fundAvailability?client_id="+client_id+"&client_secret="+client_secret, request, String.class);
				}catch(HttpClientErrorException e) {
					LOGGER.debug("Result - HttpClientErrorException");
					return false;
				}
				LOGGER.debug("Result - status (" + response.getStatusCode() + ") has body: " + response.hasBody());

				LOGGER.debug("============Method - checkFundAvailability ===================" + response.getBody());
			return true;
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			return false;
			}
		}

		public String createSignatureForPayments(CreatePaymentRequest createPaymentRequest) {
			LOGGER.debug("============Method - createSignature ======started=============");

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("tppId", "singpaymentdata");
			try {

				String subscribeRequest = "{\r\n" + "          \"debtor\":{\r\n" + "            \"bankId\": \"\",\r\n"
						+ "            \"accountId\": \"" + createPaymentRequest.getDebtAccountId() + "\"\r\n"
						+ "          },\r\n" + "          \"creditor\": {\r\n" + "            \"bankId\": \"\",\r\n"
						+ "            \"accountId\": \"" + createPaymentRequest.getCreditAccountId() + "\"\r\n"
						+ "          },\r\n" + "          \"transactionAmount\": {\r\n" + "           \"amount\": "
						+ createPaymentRequest.getAmount() + ",\r\n" + "            \"currency\": \"EUR\",\r\n"
						+ "             \"currencyRate\": \"string\"\r\n" + "          },\r\n"
						+ "          \"endToEndId\": \"string\",\r\n"
						+ "          \"paymentDetails\": \"test sandbox\",\r\n"
						+ "          \"terminalId\": \"string\",\r\n" + "          \"branch\": \"\",\r\n"
						+ "          \"executionDate\": \"\",\r\n" + "          \"valueDate\": \"\"\r\n" + "}";
				HttpEntity<String> request = new HttpEntity<String>(subscribeRequest, headers);

				ResponseEntity<String> response = restTemplate.postForEntity(createSignature_URL, request, String.class);// .exchange(createSignature_URL,
																															// HttpMethod.POST,
																															// entity,
																															// String.class);
				LOGGER.debug("Result - status (" + response.getStatusCode() + ") has body: " + response.hasBody());

				LOGGER.debug("============Method - createSignature ===================" + response.getBody());
				return response.getBody();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
			return null;

		}

		public Payment createPaymentAftersignature(String requestBody) {
			LOGGER.debug("============Method - createPaymentAftersignature ======started=============");
			
			String tstamp = sdf.format(timestamp);

			ResponseOAuth responseOAuth = getAccessToken();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("tppId", "singpaymentdata");
			headers.add("journeyId", "journeyId");
			headers.add("Authorization", "Bearer " + responseOAuth.getAccess_token());
			headers.add("originUserId", "srinivas");
			headers.add("timeStamp", tstamp);
			headers.add("subscriptionId", subscriptionId);

			try {

				HttpEntity<String> request = new HttpEntity<String>(requestBody, headers);

				String url = "https://sandbox-apis.bankofcyprus.com/df-boc-org-sb/sb/psd2/v1/payments?client_id="+client_id+"&client_secret="+client_secret; 
				ResponseEntity<CreatePaymentResponse> response = restTemplate.postForEntity(url, request, CreatePaymentResponse.class);

				LOGGER.debug("Result - status (" + response.getStatusCode() + ") has body: " + response.hasBody());

				LOGGER.debug("============Method - createSignature ==================="
						+ response.getBody().getPayment().getPaymentId() + "                    "
						+ response.getBody());
				return new Payment(response.getBody().getPayment().getPaymentId(),
						response.getBody().getPayment().getTransactionTime());
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
			return null;

		}

		@Override
		public ApprovePaymentsResponse approvePayment(ApprovePaymentRequest approvePaymentRequest) {
			String tstamp = sdf.format(timestamp);
			LOGGER.debug("============Method - approvePayment ===================started:"
					+ approvePaymentRequest.getPaymentId());

			try {
				ResponseOAuth responseOAuth = getAccessToken();

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.add("tppId", "singpaymentdata");
				headers.add("journeyId", "journeyId");
				headers.add("Authorization", "Bearer " + responseOAuth.getAccess_token());
				headers.add("originUserId", "priyanka");
				headers.add("timeStamp", tstamp);
				headers.add("subscriptionId", subscriptionId);

				String requestBody = "{\r\n" + "  \"transactionTime\": \"" + approvePaymentRequest.getTransactionTime()
						+ "\",\r\n" + "  \"authCode\": \"123456\"\r\n" + "} ";

				LOGGER.debug("requestBody  here:" + requestBody);
				HttpEntity<String> request = new HttpEntity<String>(requestBody, headers);

				String paymentId = approvePaymentRequest.getPaymentId();
				ResponseEntity<ApprovePaymentsResponse> response = restTemplate.postForEntity("https://sandbox-apis.bankofcyprus.com/df-boc-org-sb/sb/psd2/v1/payments/"+paymentId+"/authorize?client_id="+client_id+"&client_secret="+client_secret, request, ApprovePaymentsResponse.class);

				LOGGER.debug("Result - status (" + response.getStatusCode() + ") has body: " + response.hasBody());

				LOGGER.debug(	"============Method - approvePayment ===================" + response.getBody().getRefNumber());
				ApprovePaymentsResponse obj = new ApprovePaymentsResponse();
				obj.setCode(response.getBody().getCode());
				obj.setRefNumber(response.getBody().getRefNumber());
				return obj;
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
			return null;
		}
//		@Override
//		public UserAccount viewAccountDetails(String accountId) {
//			 LOGGER.debug("============Method - viewAccountDetails ======started============="+subscriptionId);
//			 String tstamp = sdf.format(timestamp);
//			 ResponseOAuth responseOAuth = getAccessToken();
//
//	       HttpHeaders headers = new HttpHeaders();
//	       headers.setContentType(MediaType.APPLICATION_JSON);
//	       headers.add("journeyId", journeyId);
//	       headers.add("subscriptionId", subscriptionId);
//	       headers.add("originUserId", originUserId);
//	       headers.add("timeStamp", tstamp);
//	       headers.add("tppId", tppId);       
//	       headers.add("Authorization",  "Bearer "+responseOAuth.getAccess_token());//"AAIkZGE4MGRjZTMtNjU0YS00MTczLWIzYmMtMThlZTYyYWY3OGM38mUndJMHbhmKZWaeFtLHUIBpkIHwu9SV1k4CH9Pj8nRCPr1_C2R3lBPfAJcHve0ioyD2Oaex9BB_PLWi0s7uv8BofMq-pnhTTa6pxfzNX3zRsIx6yB5wnSgED2inBalXetfIsXdPOdALmBFg3ivtLw"); // first access token
//	   
//	       com.capco.capcopay.response.UserAccount account = null;
//	       try {       	
//	        	   
//	    	   HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//	    	   ResponseEntity<com.capco.capcopay.response.UserAccount> response = restTemplate.exchange(viewAccounts_URL+"/"+accountId+"?client_id="+client_id+"&client_secret="+client_secret, HttpMethod.GET, entity, com.capco.capcopay.response.UserAccount.class);
//	           LOGGER.debug("Result - status ("+ response.getStatusCode() + ") has body: " + response.hasBody());
//	        	
//	       	 	LOGGER.debug("============Method - viewAccountDetails ==================="+response.getBody());
//	     
////	       	subscriptionId = response.getBody().getSubscriptionId();
//	       	LOGGER.debug(response);
//	       	account = response.getBody();
//	       } catch (Exception e) {
//	    	   e.getMessage();
//	           e.printStackTrace();
//	       } 
//	       return account;
//		}
	//}

}

