package com.capco.capcopay.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.capco.capcopay.response.ResponseOAuth;
import com.capco.capcopay.service.CitiBankService;

@Service
public class CitiBankServiceImpl implements CitiBankService {

	final String client_id ="54849e1e-318b-4394-bdf4-6511099a572e";
	final String client_secret = "V7tH7lP7xW4mF7hR3aN1eT6pL3aH4vV5rT2yN8eN1jW1vY3sH8";
	final String access_token = "AAIkMzkwNmRkNmQtNTM0Yi00ZDIwLTgxZDctMGU3ODg0ODAxM2Ezyr51Z3W4MrAHTLR85pxRYQOuhsbzmACvr0QXj6EE_ag-jQqy3ycd1wQf5Jm9jXELQHXidfC4NVtHFvXz0WsIcSbMod7p2i_WyAxvHPndm4GLd3V1uEb6cF0QzSjjpxdtI5_qV0GUoeE562fnTZRKFC3Y353rriSY_d9hUgD7e6pdcztMPt2vknyaYkqIhDEgKWsjzWShMkpLmJSJo5nMtDMC35okd3LvJfkk9ejsM9z0ufopy_-2asOcsoCwwUu4NGW2S73pTl59Ok5vdlz2j3Hoiq7eZWd9ohIJzCGxoAkL0rlWII6ZuFC_SLvY_N-PRO964mtDS2FiB9cd4Jw31W-Q505p_Sp0KnxzoGfBlmJtzuDfal65hbn23R67lt-ytmo5KzTqx5UCcs_MScdUHOcuXjUqPWqSatZPo6l5Wao";
	final String refresh_token = "AALwvl9hJqEwUDLhcFSANB6hGgBCirfms-ZbENg7X6F3uCkkukyE3PEzo6TkEFssyS-qbaKTzXML0nicZ4nMPAPoMe62I7F0EP65mMbpPtnvXB10_XhRDmAron08wY_PFeTC0dT2iOs3oI0D4cU5vM7qFFR5IB2VNNcNIgCYdjhvn173JrXL-IeZdqAI8gGwic_suvTTlTZQC4aefC65BdQZDI4woTPznZjOfLZyg5WEt9KVtPlqoTyX-xCpKuDKi5Raof-kWpqfzHhBjG3BIi8-BH-DgZMd_H7sBU_CyLpkrhoiNrGpYyk_EsRyKlPR7kjpzk2tm35dYXadbwkf167cjAvu0uJFLpgcutFCS5cvRIJjDzUNR4fMUlS7IB1Zbsv_qTjpZ0fOTWX-Er8Dt50TcjqYCeN-kLsgRGy6UcXG6ij-BeR3yxdcLJ88rs8Uiso";
	
	final RestTemplate restTemplate;
	
	public CitiBankServiceImpl(RestTemplate restTemplate) {
		this.restTemplate=restTemplate;
	}
	@Override
	public void getAccessToken() {
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
//        map.add("client_id", client_id);
//        map.add("client_secret", client_secret);
        map.add("grant_type", "mokfocip");
        map.add("code", "AALHh2RQQTFJMDCCrjPynrHfGWknnTDbEHzTBe0RSff6yj6N89M8RrT9GKh-mRGJ7I3YPVYCzKBbQ_mFEJGf8hB40VfiFZA5mnr5l8jGucMdA_X5XsbB02y-KVRgJF5RBky6cLw76iuV4FoI8ymPKqOC9c-38ebV8kPxPtPt2JdV8O_mH737EEgNmFhYODWHoxR0PuY_Zy2QKQUkwkwl4Wa0KPUHzg3dffBKsGA-2chcim4-moOw588N-2yQbs4OXdE8m_JSu7dGqcTcyw3F8wLyPNbK2vQYhacavb0-I2ykhnYD-8e-8_4mNjQ8sckNV4E");
        map.add("redirect_uri", "http://localhost:4200/capcopay/dashboard");
        
        ResponseOAuth body = null;
        
        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(MediaType.APPLICATION_JSON);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        
        try {
        	ResponseEntity<String> response = restTemplate.postForEntity("https://sandbox.apihub.citi.com/gcb/api/authCode/oauth2/token/us/gcb", request , String.class);
//        	body = response.getBody();
        	System.out.println("-------first access toekn---------"+response);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
//        	  --header 'authorization: REPLACE_THIS_VALUE' \
	}

}
