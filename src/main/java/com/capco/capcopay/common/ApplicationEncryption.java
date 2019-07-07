package com.capco.capcopay.common;

import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

import io.micrometer.core.lang.NonNull;

public class ApplicationEncryption implements Serializer<String, String> {

	private static final int ivSize=16;
	private final SecureRandom random;
	private final Key encryptionKey;
	
	public ApplicationEncryption(final String suppliedKey) {
		this.random = new SecureRandom();
		this.encryptionKey= null;
	}
	@Override
	public String dump(final @NonNull String plainText) {
		final byte[] ivRaw = new byte[ivSize];
		this.random.nextBytes(ivRaw);
		final AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivRaw);
		final Cipher cipher;
		final byte[] cipherText;
		try {
			cipher = Cipher.getInstance("");
//			cipher.init(Cipher.ENCRYPT_MODE, this.en, params);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public String load(String data) {
		return null;
	}

}
