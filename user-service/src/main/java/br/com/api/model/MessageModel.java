package br.com.api.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "messages")
@Component
public class MessageModel {

	private String genericError;
	private String genericInfo;
	private String errorToGeneratePasswordEncrypted;
	private String userAlreadyRegistered;
	private String phoneAlreadyRegistered;
	private String wrongPasswordOrEmail;
	private String emailNotValid;
	private String emailDoesNotMatch;
	private String passwordDoesNotMatch;

	public String getGenericError() {
		return genericError;
	}

	public void setGenericError(String genericError) {
		this.genericError = genericError;
	}

	public String getGenericInfo() {
		return genericInfo;
	}

	public void setGenericInfo(String genericInfo) {
		this.genericInfo = genericInfo;
	}

	public String getErrorToGeneratePasswordEncrypted() {
		return errorToGeneratePasswordEncrypted;
	}

	public void setErrorToGeneratePasswordEncrypted(String errorToGeneratePasswordEncrypted) {
		this.errorToGeneratePasswordEncrypted = errorToGeneratePasswordEncrypted;
	}

	public String getUserAlreadyRegistered() {
		return userAlreadyRegistered;
	}

	public void setUserAlreadyRegistered(String userAlreadyRegistered) {
		this.userAlreadyRegistered = userAlreadyRegistered;
	}

	public String getPhoneAlreadyRegistered() {
		return phoneAlreadyRegistered;
	}

	public void setPhoneAlreadyRegistered(String phoneAlreadyRegistered) {
		this.phoneAlreadyRegistered = phoneAlreadyRegistered;
	}

	public String getWrongPasswordOrEmail() {
		return wrongPasswordOrEmail;
	}

	public void setWrongPasswordOrEmail(String wrongPasswordOrEmail) {
		this.wrongPasswordOrEmail = wrongPasswordOrEmail;
	}

	public String getEmailNotValid() {
		return emailNotValid;
	}

	public void setEmailNotValid(String emailNotValid) {
		this.emailNotValid = emailNotValid;
	}

	public String getEmailDoesNotMatch() {
		return emailDoesNotMatch;
	}

	public void setEmailDoesNotMatch(String emailDoesNotMatch) {
		this.emailDoesNotMatch = emailDoesNotMatch;
	}

	public String getPasswordDoesNotMatch() {
		return passwordDoesNotMatch;
	}

	public void setPasswordDoesNotMatch(String passwordDoesNotMatch) {
		this.passwordDoesNotMatch = passwordDoesNotMatch;
	}
}
