package br.com.api.controller.interfaces;

import java.util.List;

import br.com.api.entity.UserEntity;

public interface UserResource {
	
	String save();
	
	List<UserEntity> findAllUsers();
	
	void deleteUser(Long id);
	
	String editUser(Long id);
	
	String editPassword(Long id);
	
	void addPhone();
	
	String checkPassword();
	
	void deletePhone();
}
