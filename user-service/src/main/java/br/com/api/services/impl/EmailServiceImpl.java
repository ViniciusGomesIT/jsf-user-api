package br.com.api.services.impl;

import java.util.Arrays;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.api.entity.UserEntity;
import br.com.api.model.EmailProperties;
import br.com.api.model.MessageModel;
import br.com.api.repository.UserRepository;
import br.com.api.response.EmailSenderResponse;
import br.com.api.services.interfaces.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	private MessageModel message;
	private EmailProperties emailProperties;
	private JavaMailSender mailSender;
	private UserRepository userRepository;
	
	@Inject
	public EmailServiceImpl(EmailProperties emailProperties, MessageModel message, JavaMailSender mailSender,
			UserRepository userRepository) {
		this.emailProperties = emailProperties;
		this.message = message;
		this.mailSender = mailSender;
		this.userRepository = userRepository;
	}

	@Override
	public EmailSenderResponse sendEmail(String email) {
		EmailSenderResponse response = new EmailSenderResponse();
		
		SimpleMailMessage mailMessage = configureMailMessage(email);
		
		response.setIsEmailSent(false);
		
        try {
        	if ( isValidUser(email) ) {
        		mailSender.send(mailMessage);
                response.setIsEmailSent(true);
        	}
        	
            return response;
        } catch (Exception e) {
        	LOGGER.error(Arrays.toString(e.getStackTrace()));
        	FacesContext.getCurrentInstance().addMessage("EmailSenderService", new FacesMessage(message.getEmailSendError()));
        	
        	response.setErrorMessage(message.getEmailSendError());
        	
            return response;
        }
	}

	private SimpleMailMessage configureMailMessage(String email) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setFrom(emailProperties.getEmailSender());
		mailMessage.setTo(email);
		
		mailMessage.setText(message.getEmailReminderPasswordMessage());
		mailMessage.setSubject(message.getResetPasswordEmailSubject());
		
		return mailMessage;
	}

	private boolean isValidUser(String email) {
		Optional<UserEntity> userOpt = this.userRepository.findByEmailIgnoreCase(email);
		
		return userOpt.isPresent();
	}
}
