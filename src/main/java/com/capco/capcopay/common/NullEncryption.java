package com.capco.capcopay.common;

import javax.validation.constraints.NotNull;

public class NullEncryption implements Serializer<String, String>{

	@Override
	public String dump(@NotNull String data) {
		return data;
	}

	@Override
	public String load(@NotNull String data) {
		return data;
	}

}
