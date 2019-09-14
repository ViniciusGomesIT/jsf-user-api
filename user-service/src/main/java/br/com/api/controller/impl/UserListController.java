package br.com.api.controller.impl;

import java.util.List;

import javax.inject.Inject;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.api.controller.resource.UserListResource;
import br.com.api.entity.UserEntity;
import br.com.api.repository.UserRepository;

@Scope (value = "session")
@Component (value = "userList")
@ELBeanName(value = "userList")
@Join(path = "/list", to = "/user-list.jsf")
public class UserListController implements UserListResource {
	
	private UserRepository repository;
	List<UserEntity> userList;
	
	@Inject
	public UserListController(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	@Deferred
    @RequestAction
    @IgnorePostback
	public void findAllUsers() {
		this.userList = repository.findAll();
	}
	
	public List<UserEntity> getUserList() {
		return userList;
	}
}
