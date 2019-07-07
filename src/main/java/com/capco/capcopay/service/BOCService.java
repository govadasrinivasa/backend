package com.capco.capcopay.service;

import com.capco.capcopay.dto.ProfileDto;
import com.capco.capcopay.dto.SelectedAccountDetailsDto;
import com.capco.capcopay.dto.SubscribeDto;
import com.capco.capcopay.request.ApprovePaymentRequest;
import com.capco.capcopay.request.CreatePaymentRequest;
import com.capco.capcopay.response.*;

public interface BOCService {
	public com.capco.capcopay.response.UserAccount[] viewSelectedAccountDetail(SelectedAccountDetailsDto selectedAccounts); 
	public com.capco.capcopay.response.UserAccount[] viewAllAccountDetails(SubscribeDto subscribeDto);
	public ResponseOAuth getAccessToken();
	public SubscriptionResponse subscribe(SubscribeDto subscribeDto);
	public ResponseOAuth getSecondAccessToken(ProfileDto profileDtoS);
	public UpdateSubscribeResponse updateSubscribe(ProfileDto profileDto);
	public RegisteredBankNames getUserBankList(SubscribeDto email);
	public UserAccountListResponse getUserAccountDetails(SubscribeDto email);
	public UserFeatureResponse userBOCFeatures(SubscribeDto email);
	
	public ApprovePaymentsResponse createPayment(CreatePaymentRequest createPaymentRequest);
	public ApprovePaymentsResponse approvePayment(ApprovePaymentRequest approvePaymentRequest);
//	public com.capco.capcopay.response.UserAccount viewAccountDetails(String accountId);
}
