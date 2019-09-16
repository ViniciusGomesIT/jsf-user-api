package br.com.api.services.interfaces;

import br.com.api.request.ResetPasswordRequest;
import br.com.api.request.SaveUserRequest;
import br.com.api.response.UserListResponse;
import br.com.api.response.UserResponse;

public interface UserService {

	UserResponse saveUser(SaveUserRequest saveUserRequest);
	
	UserListResponse findAllUsers();
	
	void deleteById(Long id);
	
	SaveUserRequest editUser(Long id);
	
	UserResponse checkAndUpdatePassword(ResetPasswordRequest passwordRequest);
}
