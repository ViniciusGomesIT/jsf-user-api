package br.com.api.controller.impl;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.api.controller.interfaces.UserResource;
import br.com.api.entity.PhoneEntity;
import br.com.api.entity.UserEntity;
import br.com.api.request.ResetPasswordRequest;
import br.com.api.request.SaveUserRequest;
import br.com.api.response.UserListResponse;
import br.com.api.response.UserResponse;
import br.com.api.services.interfaces.UserService;

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
		FacesContext.getCurrentInstance().addMessage("UserController", new FacesMessage(userResponse.getMessageError()));
		
		return "/pages/register";
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
		
		this.saveUserRequest.setRenderReset(false);
		this.saveUserRequest.setRenderPassword(false);
		
		
		if ( saveUserRequest.getMessage().isEmpty() ) {
			return "/pages/register.xhtml?faces-redirect=true";
		}
		FacesContext.getCurrentInstance().addMessage("UserController", new FacesMessage(saveUserRequest.getMessage()));
		
		return "/pages/list.xhtml";
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
			return "/pages/list.xhtml?faces-redirect=true";
		}
		
		FacesContext.getCurrentInstance().addMessage("UserController", new FacesMessage(userResponse.getMessageError()));
		
		return "/pages/edit-password";
	}
	
	@Override
	public void addPhone() {
		this.saveUserRequest.getPhones().add(new PhoneEntity());
	}
	
	@Override
	public void deletePhone() {
		if ( !this.saveUserRequest.getPhones().isEmpty() && this.saveUserRequest.getPhones().size() >= 1 ) {
			this.saveUserRequest.getPhones().remove(this.saveUserRequest.getPhones().size() - 1);
		}
	}
	
	public SaveUserRequest getSaveUserRequest() {
		return saveUserRequest;
	}

	public ResetPasswordRequest getPasswordRequest() {
		return passwordRequest;
	}
}
