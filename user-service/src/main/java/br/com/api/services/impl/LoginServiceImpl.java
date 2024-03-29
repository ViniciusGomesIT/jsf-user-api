package br.com.api.services.impl;

import java.io.Serializable;
import java.util.Base64;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.api.entity.UserEntity;
import br.com.api.model.MessagePropertiesModel;
import br.com.api.repository.UserRepository;
import br.com.api.response.UserResponse;
import br.com.api.services.interfaces.LoginService;

@Service
public class LoginServiceImpl implements LoginService, Serializable {
	
	private static final long serialVersionUID = 1508727838689411957L;
	
	private UserRepository userRepository;
	private MessagePropertiesModel message;
	private UserResponse response;

	@Inject
	public LoginServiceImpl(UserRepository userRepository, MessagePropertiesModel message) {
		this.userRepository = userRepository;
		this.message = message;
	}

	@Override
	public UserResponse authenticateUser(UserEntity providedUserInfos) {
		response = new UserResponse();
		Optional<UserEntity> userOptFromBase = userRepository.findByEmailIgnoreCase(providedUserInfos.getEmail());
		
		if ( userOptFromBase.isPresent() && checkPassword(userOptFromBase.get().getPassword(), providedUserInfos.getPassword()) ) {
			response.setUser(userOptFromBase); 
			
			return response;
		}
		
		response.setUser(Optional.empty());
		response.setErrorMessage(message.getWrongPasswordOrEmail());

		return response;
	}

	private Boolean checkPassword(String passwordFromBase, String givenPassword) {
		return givenPassword.matches(new String(Base64.getDecoder().decode(passwordFromBase)));
	}
	
}
