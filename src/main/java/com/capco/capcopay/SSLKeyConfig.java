package com.capco.capcopay;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SSLKeyConfig {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) throws Exception {
		SSLConnectionSocketFactory scsf = new SSLConnectionSocketFactory(
				org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy())
				.build(), NoopHostnameVerifier.INSTANCE);
		
		HttpClient client = HttpClients.custom().setSSLSocketFactory(scsf).build();
		ClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);
		BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory = new BufferingClientHttpRequestFactory(factory);
		RestTemplate restTemplate = builder.build();
		restTemplate.setRequestFactory(bufferingClientHttpRequestFactory);
		
		return restTemplate;
	}
}
