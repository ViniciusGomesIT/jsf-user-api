package br.com.api.controller.impl;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.api.controller.resource.UserResource;
import br.com.api.entity.PhoneEntity;
import br.com.api.entity.UserEntity;
import br.com.api.request.ResetPasswordRequest;
import br.com.api.request.SaveUserRequest;
import br.com.api.response.UserListResponse;
import br.com.api.response.UserResponse;
import br.com.api.services.UserService;

@SessionScope
@Component(value = "userController")
public class UserController implements UserResource, Serializable {

	private static final long serialVersionUID = 6951455217893241782L;

	private UserService service;
	
	private SaveUserRequest saveUserRequest;
	private ResetPasswordRequest passwordRequest;
	private UserResponse userResponse;

	@Inject
	public UserController(UserService service) {
		this.service = service;
		this.saveUserRequest = new SaveUserRequest();
		this.passwordRequest = new ResetPasswordRequest();
	}

	@Override
	public String save() {
		userResponse = this.service.saveUser(saveUserRequest);
		
		if ( userResponse.getUser().isPresent() ) {
			return "/pages/list.xhtml?faces-redirect=true";
		}
		FacesContext.getCurrentInstance().addMessage("UserController", new FacesMessage(saveUserRequest.getMessage()));
		
		return "/pages/register?faces-redirect=true";
	}
	
	@Override
	public List<UserEntity> findAllUsers() {
		UserListResponse response = service.findAllUsers();
		
		return response.getUserList();
	}
	
	@Override
	public void deleteUser(Long id) {
		service.deleteById(id);
	}
	
	@Override
	public String editUser(Long id) {
		this.saveUserRequest = service.editUser(id);
		this.saveUserRequest.setRenderPassword(false);
		this.saveUserRequest.setRenderReset(false);
		
		if ( saveUserRequest.getMessage().isEmpty() ) {
			return "/pages/register.xhtml?faces-redirect=true";
		}
		FacesContext.getCurrentInstance().addMessage("UserController", new FacesMessage(userResponse.getMessageError()));
		
		return "/pages/list.xhtml?faces-redirect=true";
	}
	
	@Override
	public String editPassword(Long id) {
		this.passwordRequest.setId(id);
		return "/pages/edit-password?faces-redirect=true";
	}
	
	@Override
	public String checkPassword() {
		userResponse = this.service.checkAndUpdatePassword(passwordRequest);
		
		if (userResponse.getMessageError().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("UserController", new FacesMessage(userResponse.getMessages()));
			return "/pages/list.xhtml?faces-redirect=true";
		}
		
		FacesContext.getCurrentInstance().addMessage("UserController", new FacesMessage(userResponse.getMessageError()));
		
		return "/pages/edit-password?faces-redirect=true";
	}

	@Override
	public void addPhone(PhoneEntity phone) {
		this.saveUserRequest.getPhones().add(new PhoneEntity());
	}
	
	public SaveUserRequest getSaveUserRequest() {
		return saveUserRequest;
	}

	public ResetPasswordRequest getPasswordRequest() {
		return passwordRequest;
	}
}
