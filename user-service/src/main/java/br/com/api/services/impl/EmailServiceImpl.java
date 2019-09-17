package br.com.api.services.impl;

import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import br.com.api.entity.UserEntity;
import br.com.api.model.EmailProperties;
import br.com.api.model.MessagePropertiesModel;
import br.com.api.repository.UserRepository;
import br.com.api.response.EmailSenderResponse;
import br.com.api.services.interfaces.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	private MessagePropertiesModel message;
	private EmailProperties emailProperties;
	private JavaMailSender mailSender;
	private UserRepository userRepository;
	
	@Inject
	public EmailServiceImpl(MessagePropertiesModel message, EmailProperties emailProperties, 
			JavaMailSender mailSender, UserRepository userRepository) {
		this.message = message;
		this.emailProperties = emailProperties;
		this.mailSender = mailSender;
		this.userRepository = userRepository;
	}

	@Override
	public EmailSenderResponse sendEmail(String email) {
		EmailSenderResponse response = new EmailSenderResponse();
		response.setIsEmailSent(false);
		Optional<UserEntity> userEntityOpt = this.userRepository.findByEmailIgnoreCase(email);
		
		if ( !userEntityOpt.isPresent() ) {
			response.setErrorMessage(message.getEmailSendError());
			
			return response;
		}
		
		SimpleMailMessage mailMessage = configureMailMessage(userEntityOpt.get());
		JavaMailSenderImpl mailsenderImpl = (JavaMailSenderImpl) mailSender;
		
		mailsenderImpl.setPassword(new String(Base64.getDecoder().decode(emailProperties.getPassword())));
		mailsenderImpl.setUsername(new String(Base64.getDecoder().decode(emailProperties.getUsername())));
		
        try {
        	mailsenderImpl.send(mailMessage);
            response.setIsEmailSent(true);
        	
            return response;
        } catch (Exception e) {
        	LOGGER.error(Arrays.toString(e.getStackTrace()));
        	FacesContext.getCurrentInstance().addMessage("EmailSenderService", new FacesMessage(message.getEmailSendError()));
        	
        	response.setErrorMessage(message.getEmailSendError());
        	
            return response;
        }
	}

	private SimpleMailMessage configureMailMessage(UserEntity userEntity) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setFrom(new String(Base64.getDecoder().decode(emailProperties.getUsername())));
		mailMessage.setTo(userEntity.getEmail());
		
		mailMessage.setSubject(message.getResetPasswordEmailSubject());
		mailMessage.setText(String.format(message.getEmailReminderPasswordMessage(), new String(Base64.getDecoder().decode(userEntity.getPassword()))));
		
		return mailMessage;
	}
}
