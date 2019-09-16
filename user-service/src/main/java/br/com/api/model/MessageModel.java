package br.com.api.model;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "messages")
@Component
public class MessageModel implements Serializable {

	private static final long serialVersionUID = -7067557703562007762L;
	
	private String genericError;
	private String genericInfo;
	private String errorToGeneratePasswordEncrypted;
	private String userAlreadyRegistered;
	private String phoneAlreadyRegistered;
	private String wrongPasswordOrEmail;
	private String emailNotValid;
	private String emailDoesNotMatch;
	private String userNotFound;
	private String oldPasswordDoesNotMatch;
	private String passwordChangedSucess;

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

	public String getUserNotFound() {
		return userNotFound;
	}

	public void setUserNotFound(String userNotFound) {
		this.userNotFound = userNotFound;
	}

	public String getOldPasswordDoesNotMatch() {
		return oldPasswordDoesNotMatch;
	}

	public void setOldPasswordDoesNotMatch(String oldPasswordDoesNotMatch) {
		this.oldPasswordDoesNotMatch = oldPasswordDoesNotMatch;
	}

	public String getPasswordChangedSucess() {
		return passwordChangedSucess;
	}

	public void setPasswordChangedSucess(String passwordChangedSucess) {
		this.passwordChangedSucess = passwordChangedSucess;
	}

}
