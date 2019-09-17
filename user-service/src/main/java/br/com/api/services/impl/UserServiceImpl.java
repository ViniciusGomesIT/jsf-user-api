package br.com.api.services.impl;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.api.builders.AddressBuilder;
import br.com.api.builders.SaveUserRequestBuilder;
import br.com.api.builders.UserEntityBuilder;
import br.com.api.entity.AddressEntity;
import br.com.api.entity.UserEntity;
import br.com.api.model.MessagePropertiesModel;
import br.com.api.repository.UserRepository;
import br.com.api.request.ResetPasswordRequest;
import br.com.api.request.SaveUserRequest;
import br.com.api.response.UserListResponse;
import br.com.api.response.UserResponse;
import br.com.api.security.utils.GenerateMD5;
import br.com.api.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService, Serializable {
	
	private static final long serialVersionUID = -6733962411945021876L;
	
	private UserRepository userRepository;
	private MessagePropertiesModel message;
	private UserResponse userResponse;
	
	@Inject
	public UserServiceImpl(UserRepository userRepository, MessagePropertiesModel message) {
		this.userRepository = userRepository;
		this.message = message;
	}

	@Override
	public UserResponse saveUser(SaveUserRequest saveUserRequest) {
		UserEntity user;
		
		if ( null != saveUserRequest.getId()) {
			return updateUser(saveUserRequest);
		}
		
		userResponse = new UserResponse();
		
		checkEmailRegistered(saveUserRequest);
		
		checkConfirmEmail(saveUserRequest);
		
		user = buildUserEntity(saveUserRequest);
		
		user.setPassword(Base64.getEncoder().encodeToString(saveUserRequest.getPassword().getBytes()));
		
		user.setRegistrationDate(new Date());
		
		if ( userResponse.getMessageError().isEmpty() ) {
			userResponse.setUser( userRepository.save(user) );
		} 
		
		return userResponse;
	}
	
	@Override
	public UserResponse updateUser(SaveUserRequest saveUserRequest) {
		UserEntity user;
		userResponse = new UserResponse();
		
		user =  this.userRepository.findById(saveUserRequest.getId()).get();
		
		if ( !user.getEmail().equalsIgnoreCase(saveUserRequest.getEmail()) ) {
			checkEmailRegistered(saveUserRequest);
		}
		
		checkConfirmEmail(saveUserRequest);
		
		user = buildUserEntity(saveUserRequest);
		
		if ( userResponse.getMessageError().isEmpty() ) {
			userResponse.setUser( userRepository.save(user) );
		} 
		
		return userResponse;
	}

	@Override
	public UserListResponse findAllUsers() {
		UserListResponse userListResponse = new UserListResponse();
		
		List<UserEntity> userList = userRepository.findAll();
		
		userList.forEach(user -> user.setPassword(GenerateMD5.generate(user.getPassword())));
		
		userListResponse.setUserList(userList);
		
		return userListResponse;
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public SaveUserRequest editUser(Long id) {
		SaveUserRequest saveUserRequest = new SaveUserRequest();
		Optional<UserEntity> userOpt = this.userRepository.findById(id);
		
		if ( userOpt.isPresent() ) {
			return buildSaveUserRequest(userOpt.get());
		}
		
		saveUserRequest.setMessage(message.getUserNotFound());
		return saveUserRequest;
	}
	
	@Override
	public UserResponse checkAndUpdatePassword(ResetPasswordRequest passwordRequest) {
		userResponse = new UserResponse();
		Optional<UserEntity> userOpt = this.userRepository.findById(passwordRequest.getId());
		UserEntity user;
		
		if ( userOpt.isPresent() ) {
			user = userOpt.get();
			String oldPassword = new String(Base64.getDecoder().decode(user.getPassword()));
			if (oldPassword.matches(passwordRequest.getOldPassword())) {
				
				user.setPassword(Base64.getEncoder().encodeToString(passwordRequest.getNewPassword().getBytes()));
				userRepository.save(user);
				userResponse.setUser(user);
				userResponse.setMessage(message.getPasswordChangedSucess());
			} else {
				userResponse.setErrorMessage(message.getOldPasswordDoesNotMatch());
			}
		}
		
		return userResponse;
	}
	
	private void checkConfirmEmail(SaveUserRequest saveUserRequest) {
		if ( !saveUserRequest.getEmail().equalsIgnoreCase(saveUserRequest.getEmailConfirm()) ) {
			userResponse.setErrorMessage(message.getEmailDoesNotMatch());
		}
	}
	
	private UserEntity buildUserEntity(SaveUserRequest saveUserRequest) {
		AddressEntity address = buidAddressEntity(saveUserRequest);
				
		return new UserEntityBuilder()
				.withId(saveUserRequest.getId())
				.withName(saveUserRequest.getName())
				.withEmail(saveUserRequest.getEmail())
				.withPassword(saveUserRequest.getPassword())
				.withRegistrationDate(saveUserRequest.getRegistrationDate())
				.withGender(saveUserRequest.getGender())
				.withMaritalStatus(saveUserRequest.getMaritalStatus())
				.withDateOfBirth(saveUserRequest.getDateOfBirth())
				.withAddress(address)
				.withPhones(saveUserRequest.getPhones())
				.build();
	}
	
	private AddressEntity buidAddressEntity(SaveUserRequest saveUserRequest) {
		return new AddressBuilder()
				.withAddressName(saveUserRequest.getAddressName())
				.withAddressComplement(saveUserRequest.getAddressComplement())
				.withAddressPostalCode(saveUserRequest.getAddressPostalCode())
				.withNeighborhood(saveUserRequest.getNeighborhoodName())
				.withCity(saveUserRequest.getCityName())
				.withState(saveUserRequest.getStateName())
				.build();
	}

	private void checkEmailRegistered(SaveUserRequest saveUserRequest) {
		Optional<UserEntity> userOpt = userRepository.findByEmailIgnoreCase(saveUserRequest.getEmail());
		
		if ( userOpt.isPresent() ) {
			userResponse.setErrorMessage(message.getUserAlreadyRegistered());
		}
	}
	
	private SaveUserRequest buildSaveUserRequest(UserEntity userEntity) {
		return new SaveUserRequestBuilder()
				.withId(userEntity.getId())
				.withName(userEntity.getName())
				.withEmail(userEntity.getEmail())
				.withEmailConfirm(userEntity.getEmail())
				.withPassword(userEntity.getPassword())
				.withPasswordConfirm(userEntity.getPassword())
				.withRegistrationDate(userEntity.getRegistrationDate())
				.withGender(userEntity.getGender())
				.withMaritalStatus(userEntity.getMaritalStatus())
				.withDateOfBirth(userEntity.getDateOfBirth())
				.withAddressName(userEntity.getAddress().getAddressName())
				.withAddressComplement(userEntity.getAddress().getAddressComplement())
				.withAddressPostalCode(userEntity.getAddress().getPostalCode())
				.withNeighborhoodName(userEntity.getAddress().getNeighborhoodName())
				.withCityName(userEntity.getAddress().getCityName())
				.withStateName(userEntity.getAddress().getStateName())
				.withPhones(userEntity.getPhones())
				.build();
	}
}
