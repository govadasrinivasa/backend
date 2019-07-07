package com.capco.capcopay.util;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Test {

	public static void main(String[] args) throws IOException {
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials&scope=remso");
		Request request = new Request.Builder()
		  .url("https://sandbox.apihub.citi.com/gcb/api/clientCredentials/oauth2/token/in/gcb")
		  .post(body)
		  .addHeader("authorization", "KGNsaWVudF9pZDpjbGllbnRfc2VjcmV0KQ==")
		  .addHeader("content-type", "application/x-www-form-urlencoded")
		  .addHeader("accept", "application/json")
		  .build();

		Response response = client.newCall(request).execute();
		System.out.println(response.toString());
	}

}
