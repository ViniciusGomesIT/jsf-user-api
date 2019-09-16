package br.com.api.request;

public class GenericRequest {
	
	private StringBuilder messageBuilder;
	private static final String EMPTY_STRING = new String();

	public String getMessage() {
		if ( null == messageBuilder ) {
			return EMPTY_STRING;
		}
		
		return messageBuilder.toString();
	}

	public void setMessage(String message) {
		if ( null == messageBuilder ) {
			messageBuilder = new StringBuilder();
		}
		
		if ( messageBuilder.length() == 0 ) {
			messageBuilder.append(message);
		} else {
			messageBuilder.append("\n");
			messageBuilder.append(message);
		}
	}

}
