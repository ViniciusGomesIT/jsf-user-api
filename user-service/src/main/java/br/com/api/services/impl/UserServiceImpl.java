package br.com.api.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.api.builders.AddressBuilder;
import br.com.api.builders.SaveUserRequestBuilder;
import br.com.api.builders.UserEntityBuilder;
import br.com.api.entity.AddressEntity;
import br.com.api.entity.PhoneEntity;
import br.com.api.entity.UserEntity;
import br.com.api.model.MessageModel;
import br.com.api.repository.PhoneRepository;
import br.com.api.repository.UserRepository;
import br.com.api.request.ResetPasswordRequest;
import br.com.api.request.SaveUserRequest;
import br.com.api.response.UserListResponse;
import br.com.api.response.UserResponse;
import br.com.api.services.UserService;
import br.com.api.utils.security.GenerateMD5;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private PhoneRepository phoneRepository;
	private MessageModel message;
	private UserResponse userResponse;
	
	@Inject
	public UserServiceImpl(UserRepository userRepository, PhoneRepository phoneRepository, MessageModel message) {
		this.userRepository = userRepository;
		this.phoneRepository = phoneRepository;
		this.message = message;
	}

	@Override
	public UserResponse saveUser(SaveUserRequest saveUserRequest) {
		UserEntity user;
		userResponse = new UserResponse();
		
		checkConfirmEmail(saveUserRequest);
		
		checkEmailRegistered(saveUserRequest);
		
		checkPhonesRegistered(saveUserRequest);
		
		user = buildUserEntity(saveUserRequest);
		
		user.setPassword(GenerateMD5.generate(saveUserRequest.getPassword()));
		user.setRegistrationDate(new Date());
		
		if ( userResponse.getMessageError().isEmpty() ) {
			userResponse.setUser( userRepository.save(user) );
		} 
		
		return userResponse;
	}

	@Override
	public UserListResponse findAllUsers() {
		UserListResponse userListResponse = new UserListResponse();
		
		List<UserEntity> userList = userRepository.findAll();
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
		
		saveUserRequest.setStateName(message.getUserNotFound());
		return saveUserRequest;
	}
	
	@Override
	public UserResponse checkAndUpdatePassword(ResetPasswordRequest passwordRequest) {
		userResponse = new UserResponse();
		Optional<UserEntity> userOpt = this.userRepository.findById(passwordRequest.getId());
		UserEntity user;
		
		if ( userOpt.isPresent() ) {
			user = userOpt.get();
			if (user.getPassword().matches(GenerateMD5.generate(passwordRequest.getOldPassword()))) {
				
				user.setPassword(GenerateMD5.generate(passwordRequest.getNewPassword()));
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
	
	private void checkPhonesRegistered(SaveUserRequest saveUserRequest) {
		List<PhoneEntity> listPhones = saveUserRequest.getPhones()
				.stream()
				.map(phone -> phoneRepository.findByDddAndNumber(phone.getDdd(), phone.getNumber()))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
		
		if ( !listPhones.isEmpty() ) {
			userResponse.setErrorMessage(message.getPhoneAlreadyRegistered());
		}
	}
	
	private SaveUserRequest buildSaveUserRequest(UserEntity userEntity) {
		return new SaveUserRequestBuilder()
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
