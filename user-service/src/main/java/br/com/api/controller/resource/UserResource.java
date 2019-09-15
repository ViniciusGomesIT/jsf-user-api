package br.com.api.controller.resource;

import java.util.List;

import br.com.api.entity.UserEntity;

public interface UserResource {
	
	String save();
	
	List<UserEntity> findAllUsers();
	
	void deleteUser(Long id);
}
