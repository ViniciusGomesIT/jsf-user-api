package br.com.api.response;

public class GenericResponse {

	private StringBuilder messageErrorBuilder;
	private StringBuilder messages;
	private static final String EMPTY_STRING = new String();

	public String getMessageError() {
		if ( null == messageErrorBuilder ) {
			return EMPTY_STRING;
		}
		
		return messageErrorBuilder.toString();
	}

	public void setErrorMessage(String message) {
		if ( null == messageErrorBuilder ) {
			messageErrorBuilder = new StringBuilder();
		}
		
		if ( messageErrorBuilder.length() == 0 ) {
			messageErrorBuilder.append(message);
		} else {
			messageErrorBuilder.append("\n");
			messageErrorBuilder.append(message);
		}
	}
	
	public String getMessages() {
		if ( null == messages ) {
			return EMPTY_STRING;
		}
		
		return messages.toString();
	}
	
	public void setMessage(String message) {
		if ( null == messages ) {
			messages = new StringBuilder();
		}
		
		if ( messages.length() == 0 ) {
			messages.append(message);
		} else {
			messages.append("\n");
			messages.append(message);
		}
	}
}
