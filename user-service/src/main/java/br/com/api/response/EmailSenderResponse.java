package br.com.api.response;

public class EmailSenderResponse extends GenericResponse {

	private Boolean isEmailSent;

	public Boolean getIsEmailSent() {
		return isEmailSent;
	}

	public void setIsEmailSent(Boolean isEmailSent) {
		this.isEmailSent = isEmailSent;
	}
}
