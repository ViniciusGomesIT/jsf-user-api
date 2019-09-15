package br.com.api.controller.impl;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.stereotype.Component;

import br.com.api.controller.resource.UserResource;
import br.com.api.entity.UserEntity;
import br.com.api.request.SaveUserRequest;
import br.com.api.response.UserListResponse;
import br.com.api.response.UserResponse;
import br.com.api.services.UserService;

@ViewScoped
@Component(value = "userController")
public class UserController implements UserResource {

	private UserService service;
	
	private SaveUserRequest saveUserRequest;
	private UserEntity user;
	private UserResponse response;

	@Inject
	public UserController(UserService service) {
		this.service = service;
		this.saveUserRequest = new SaveUserRequest();
		this.user = new UserEntity();
	}

	@Override
	public String save() {
		response = this.service.saveUser(user, saveUserRequest);
		
		if ( response.getUser().isPresent() ) {
			return "/list.xhtml?faces-redirect=true";
		}
		
		FacesContext.getCurrentInstance().addMessage("UserController", new FacesMessage(response.getMessage()));
		
		return "/register";
	}
	
	@Override
	@Deferred
    @RequestAction
    @IgnorePostback
	public List<UserEntity> findAllUsers() {
		UserListResponse response = service.findAllUsers();
		
		return response.getUserList();
	}
	
	@Override
	public void deleteUser(Long id) {
		service.deleteById(id);
	}
	
	public SaveUserRequest getSaveUserRequest() {
		return saveUserRequest;
	}

	public UserEntity getUser() {
		return user;
	}

//	public UserResponse getResponse() {
//		return response;
//	}

}
