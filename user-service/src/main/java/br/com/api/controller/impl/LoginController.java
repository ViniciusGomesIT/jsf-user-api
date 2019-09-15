package br.com.api.controller.impl;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.com.api.controller.resource.LoginResource;
import br.com.api.entity.UserEntity;
import br.com.api.model.MessageModel;
import br.com.api.response.UserResponse;
import br.com.api.services.LoginService;

@ViewScoped
@Component (value = "loginController")
public class LoginController implements LoginResource {
	
	private LoginService loginService;
	
	private UserEntity user;
	
	@Inject
	public LoginController(LoginService service, MessageModel message) {
		this.loginService = service;
		this.user = new UserEntity();
	}

	@Override
	public String authenticateUser() {
		UserResponse userResponse = loginService.authenticateUser(user);
		
		if ( userResponse.getUser().isPresent() ) {
			return "/list.xhtml?faces-redirect=true";
		}
		
		FacesContext.getCurrentInstance().addMessage("LoginController", new FacesMessage(userResponse.getMessage()));
		
		return "/login-form.xhtml";
	}
	
	public UserEntity getUser() {
		return user;
	}	
}
