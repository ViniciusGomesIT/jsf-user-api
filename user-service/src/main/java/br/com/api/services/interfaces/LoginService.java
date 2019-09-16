package br.com.api.services.interfaces;

import br.com.api.entity.UserEntity;
import br.com.api.response.UserResponse;

public interface LoginService {
	
	UserResponse authenticateUser(UserEntity user);
}
