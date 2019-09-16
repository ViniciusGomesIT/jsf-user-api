package br.com.api.services.interfaces;

import br.com.api.response.EmailSenderResponse;

public interface EmailService {

	EmailSenderResponse sendEmail(String email);
}
