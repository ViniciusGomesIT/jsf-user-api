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
import br.com.api.model.SecurityConfig;
import br.com.api.repository.UserRepository;
import br.com.api.response.EmailSenderResponse;
import br.com.api.security.utils.GenerateAES;
import br.com.api.services.interfaces.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	private MessageModel message;
	private SecurityConfig securityConfig;
	private EmailProperties emailProperties;
	private JavaMailSender mailSender;
	private UserRepository userRepository;
	
	@Inject
	public EmailServiceImpl(MessageModel message, SecurityConfig securityConfig, EmailProperties emailProperties, 
			JavaMailSender mailSender, UserRepository userRepository) {
		this.message = message;
		this.securityConfig = securityConfig;
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
		
        try {
    		mailSender.send(mailMessage);
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
//		String passwordDecrypted = GenerateAES.decrypt(userEntity.getPassword(), securityConfig.getKey());
		
		mailMessage.setFrom(emailProperties.getEmailSender());
		mailMessage.setTo(userEntity.getEmail());
		
//		mailMessage.setText(String.format(message.getEmailReminderPasswordMessage(), passwordDecrypted));
		mailMessage.setSubject(message.getResetPasswordEmailSubject());
		
		return mailMessage;
	}
}
