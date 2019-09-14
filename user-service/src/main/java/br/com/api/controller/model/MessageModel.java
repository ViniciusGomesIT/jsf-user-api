package br.com.api.controller.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "messages")
@Component
public class MessageModel {

	private String errorToGeneratePasswordEncrypt;
	private String genericError;
	private String userAlreadyRegistered;

	public String getErrorToGeneratePasswordEncrypt() {
		return errorToGeneratePasswordEncrypt;
	}

	public void setErrorToGeneratePasswordEncrypt(String errorToGeneratePasswordEncrypt) {
		this.errorToGeneratePasswordEncrypt = errorToGeneratePasswordEncrypt;
	}

	public String getGenericError() {
		return genericError;
	}

	public void setGenericError(String genericError) {
		this.genericError = genericError;
	}

	public String getUserAlreadyRegistered() {
		return userAlreadyRegistered;
	}

	public void setUserAlreadyRegistered(String userAlreadyRegistered) {
		this.userAlreadyRegistered = userAlreadyRegistered;
	}

}
