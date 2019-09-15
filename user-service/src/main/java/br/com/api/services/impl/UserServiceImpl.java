package br.com.api.services.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.api.builders.AddressBuilder;
import br.com.api.builders.PhoneBuilder;
import br.com.api.entity.AddressEntity;
import br.com.api.entity.PhoneEntity;
import br.com.api.entity.UserEntity;
import br.com.api.model.MessageModel;
import br.com.api.repository.PhoneRepository;
import br.com.api.repository.UserRepository;
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
	private UserListResponse userListResponse;
	
	@Inject
	public UserServiceImpl(UserRepository userRepository, PhoneRepository phoneRepository, MessageModel message) {
		this.userRepository = userRepository;
		this.phoneRepository = phoneRepository;
		this.message = message;
	}

	@Override
	public UserResponse saveUser(UserEntity user, SaveUserRequest saveUserRequest) {
		userResponse = new UserResponse();
		
		checkConfirmEmail(user, saveUserRequest.getEmailConfirm());
		
		checkConfirmPassword(user, saveUserRequest.getPasswordConfirm());
		
		buildUserEntity(user, saveUserRequest);
		
		checkEmailRegistered(user);
		
		checkPhonesRegistered(user);
		
		user.setPassword(GenerateMD5.generate(user.getPassword()));
		user.setRegistrationDate(new Date());
		
		if ( userResponse.getMessage().isEmpty() ) {
			userResponse.setUser(userRepository.save(user));
		} 
		
		return userResponse;
	}

	@Override
	public UserListResponse findAllUsers() {
		userListResponse = new UserListResponse();
		
		List<UserEntity> userList = userRepository.findAll();
		userListResponse.setUserList(userList);
		
		return userListResponse;
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public UserEntity findUser(Long id) {
		Optional<UserEntity> userOpt = this.userRepository.findById(id);
		
		if ( userOpt.isPresent() ) {
			return userOpt.get();
		}
		
		return null;
	}
	
	private void checkConfirmEmail(UserEntity user, String emailConfirm) {
		if ( !user.getEmail().equalsIgnoreCase(emailConfirm) ) {
			userResponse.setMessage(message.getEmailDoesNotMatch());
		}
	}
	
	private void checkConfirmPassword(UserEntity user, String passwordConfirm) {
		if ( !user.getPassword().matches(passwordConfirm) ) {
			userResponse.setMessage(message.getPasswordDoesNotMatch());
		}
	}

	private void buildUserEntity(UserEntity user, SaveUserRequest saveUserRequest) {
		PhoneEntity phone1 = new PhoneBuilder().withDdd(saveUserRequest.getPhone1DDD()).withNumber(saveUserRequest.getPhone1Number()).withType(saveUserRequest.getPhone1Type()).build();
		PhoneEntity phone2 = new PhoneBuilder().withDdd(saveUserRequest.getPhone2DDD()).withNumber(saveUserRequest.getPhone2Number()).withType(saveUserRequest.getPhone2Type()).build();
		
		AddressEntity address = new AddressBuilder()
				.withAddressName(saveUserRequest.getAddressName())
				.withAddressComplement(saveUserRequest.getAddressComplement())
				.withAddressPostalCode(saveUserRequest.getAddressPostalCode())
				.withNeighborhood(saveUserRequest.getNeighborhoodName())
				.withCity(saveUserRequest.getCityName())
				.withState(saveUserRequest.getStateName())
				.withCountry(saveUserRequest.getCountryName())
				.build();
		
		user.setPhones(Arrays.asList(phone1));
		user.setAddress(address);
	}
	
	private void checkEmailRegistered(UserEntity user) {
		Optional<UserEntity> userOpt = userRepository.findByEmailIgnoreCase(user.getEmail());
		
		if ( userOpt.isPresent() ) {
			userResponse.setMessage(message.getUserAlreadyRegistered());
		}
	}
	
	private void checkPhonesRegistered(UserEntity user) {
		List<PhoneEntity> listPhones = user.getPhones()
				.stream()
				.map(phone -> phoneRepository.findByDddAndNumber(phone.getDdd(), phone.getNumber()))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
		
		if ( !listPhones.isEmpty() ) {
			userResponse.setMessage(message.getPhoneAlreadyRegistered());
		}
	}
}
