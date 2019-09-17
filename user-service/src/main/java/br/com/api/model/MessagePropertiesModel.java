package br.com.api.model;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "messages", ignoreUnknownFields = true)
public class MessagePropertiesModel implements Serializable {

	private static final long serialVersionUID = -7067557703562007762L;

	private String genericError;
	private String genericInfo;
	private String errorToGeneratePasswordEncrypted;
	private String userAlreadyRegistered;
	private String wrongPasswordOrEmail;
	private String emailNotValid;
	private String emailDoesNotMatch;
	private String userNotFound;
	private String oldPasswordDoesNotMatch;
	private String passwordChangedSucess;
	private String emailSendSucess;
	private String emailSendError;
	private String emailReminderPasswordMessage;
	private String resetPasswordEmailSubject;

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

	public String getEmailSendSucess() {
		return emailSendSucess;
	}

	public void setEmailSendSucess(String emailSendSucess) {
		this.emailSendSucess = emailSendSucess;
	}

	public String getEmailSendError() {
		return emailSendError;
	}

	public void setEmailSendError(String emailSendError) {
		this.emailSendError = emailSendError;
	}

	public String getEmailReminderPasswordMessage() {
		return emailReminderPasswordMessage;
	}

	public void setEmailReminderPasswordMessage(String emailReminderPasswordMessage) {
		this.emailReminderPasswordMessage = emailReminderPasswordMessage;
	}

	public String getResetPasswordEmailSubject() {
		return resetPasswordEmailSubject;
	}

	public void setResetPasswordEmailSubject(String resetPasswordEmailSubject) {
		this.resetPasswordEmailSubject = resetPasswordEmailSubject;
	}

}
