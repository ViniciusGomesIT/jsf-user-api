package br.com.api.services;

import br.com.api.entity.UserEntity;
import br.com.api.request.SaveUserRequest;
import br.com.api.response.UserListResponse;
import br.com.api.response.UserResponse;

public interface UserService {

	UserResponse saveUser(UserEntity user, SaveUserRequest saveUserRequest);
	
	UserListResponse findAllUsers();
	
	void deleteById(Long id);
	
	UserEntity findUser(Long id);
	
}
