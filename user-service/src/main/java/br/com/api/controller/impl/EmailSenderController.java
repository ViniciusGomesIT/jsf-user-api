package br.com.api.controller.impl;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.com.api.controller.interfaces.EmailSenderResource;
import br.com.api.response.EmailSenderResponse;
import br.com.api.services.interfaces.EmailService;

@ViewScoped
@Component(value = "emailSenderController")
public class EmailSenderController implements EmailSenderResource, Serializable {

	private static final long serialVersionUID = -3279789660119077386L;
	
	private EmailService emailService;
	private String email;

	@Inject
	public EmailSenderController(EmailService emailService) {
		this.emailService = emailService;
	}

	@Override
	public String sendEmail() {
		EmailSenderResponse response = this.emailService.sendEmail(email);
		
		if ( response.getIsEmailSent() ) {
			return "/pages/login.xhtml?faces-redirect=true";
		}
		
		FacesContext.getCurrentInstance().addMessage("EmailSenderController", new FacesMessage(response.getMessageError()));
		return "/pages/reminder-password.xhtml?faces-redirect=true";
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}
