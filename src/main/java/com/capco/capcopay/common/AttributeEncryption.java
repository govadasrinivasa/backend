package com.capco.capcopay.common;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AttributeEncryption implements AttributeConverter<String, String>{

	private Serializer encryption = null;
	private static boolean enableDatabaseEncryption = false;
	private static String encryptionKey = null;
	public static final String defaultSecretEncryuptionKey = "TestCapcoPay";
	
	@Override
	public String convertToDatabaseColumn(String plainText) {
		final Serializer<String, String> encryption = this.resolveEncryption();
		if(plainText == null) {
			return null;
		}
		return (String) this.encryption.dump(plainText);
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		return null;
	}

	private synchronized Serializer<String,String> resolveEncryption() {
		if(this.encryption == null) {
			final Serializer<String, String> encryption;
			if(AttributeEncryption.enableDatabaseEncryption) {
				if(encryptionKey == null) {
					encryption = new ApplicationEncryption(defaultSecretEncryuptionKey);
				} else {
					encryption = new ApplicationEncryption(encryptionKey);
				}
			} else {
				encryption = new NullEncryption();
			}
			this.encryption=encryption;
		}
		return this.encryption;
	}
}
