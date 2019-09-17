package br.com.api.controller.impl;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.api.controller.interfaces.LoginResource;
import br.com.api.entity.UserEntity;
import br.com.api.response.UserResponse;
import br.com.api.services.interfaces.LoginService;

@SessionScope
@Component (value = "loginController")
public class LoginController implements LoginResource, Serializable {
	
	private static final long serialVersionUID = -9106948530846950619L;

	private LoginService loginService;
	
	private UserEntity user;
	
	@Inject
	public LoginController(LoginService service) {
		this.loginService = service;
		this.user = new UserEntity();
	}

	@Override
	public String authenticateUser() {
		UserResponse userResponse = loginService.authenticateUser(user);
		
		if ( userResponse.getUser().isPresent() ) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			session.setAttribute("userLogged", userResponse.getUser().get());
			
			return "/pages/list.xhtml?faces-redirect=true";
		}
		
		FacesContext.getCurrentInstance().addMessage("LoginController", new FacesMessage(userResponse.getMessageError()));
		
		return "/pages/login.xhtml";
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "/pages/login.xhtml?faces-redirect=true";
	}
	
	public UserEntity getUser() {
		return user;
	}	
}
