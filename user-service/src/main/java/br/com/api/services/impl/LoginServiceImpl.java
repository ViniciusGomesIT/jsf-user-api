package br.com.api.services.impl;

import java.io.Serializable;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.api.entity.UserEntity;
import br.com.api.model.MessageModel;
import br.com.api.repository.UserRepository;
import br.com.api.response.UserResponse;
import br.com.api.security.utils.GenerateMD5;
import br.com.api.services.LoginService;

@Service
public class LoginServiceImpl implements LoginService, Serializable {
	
	private static final long serialVersionUID = 1508727838689411957L;
	
	private UserRepository userRepository;
	private MessageModel message;
	private UserResponse response;

	@Inject
	public LoginServiceImpl(UserRepository userRepository, MessageModel message) {
		this.userRepository = userRepository;
		this.message = message;
	}

	@Override
	public UserResponse authenticateUser(UserEntity user) {
		response = new UserResponse();
		Optional<UserEntity> userOpt = userRepository.findByEmailIgnoreCase(user.getEmail());
		
		if ( userOpt.isPresent() && checkPassword(userOpt.get().getPassword(), user.getPassword()) ) {
			response.setUser(userOpt); 
			
			return response;
		}
		
		response.setUser(Optional.empty());
		response.setErrorMessage(message.getWrongPasswordOrEmail());

		return response;
	}

	private boolean checkPassword(String passwordFromBase, String givenPassword) {
		return passwordFromBase.matches(GenerateMD5.generate(givenPassword));
	}
	
}
