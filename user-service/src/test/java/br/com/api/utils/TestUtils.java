package br.com.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.api.entity.AddressEntity;
import br.com.api.entity.PhoneEntity;
import br.com.api.entity.UserEntity;
import br.com.api.request.SaveUserRequest;
import br.com.api.response.EmailSenderResponse;
import br.com.api.response.UserResponse;

public class TestUtils {

	public EmailSenderResponse generateOneEmailSenderResponse(boolean isEmailSent, String errorMessage, String message) {
		EmailSenderResponse response = new EmailSenderResponse();
		
		response.setErrorMessage(errorMessage);
		response.setMessage(message);
		response.setIsEmailSent(isEmailSent);
		
		return response;
	}

	public Optional<UserEntity> generateOneOptionalUserEntity() {
		UserEntity userEntity = new UserEntity();
		
		userEntity.setEmail("teste@email.com.br");
		userEntity.setPassword("somePassword");
		userEntity.setAddress(generateOneOptionalAddress().get());
		
		return Optional.of(userEntity);
	}

	public UserResponse generateOneUserResponse(boolean generateUser, String errorMessage, String message) {
		UserResponse response = new UserResponse();
		
		if (generateUser) {
			response.setUser(this.generateOneOptionalUserEntity());
		}
		
		response.setErrorMessage(errorMessage);
		response.setMessage(message);
		
		return response;
	}

	public List<UserEntity> generateOneOptionalUserEntityList() {
		List<UserEntity> userList = new ArrayList<>();
		
		userList.add(generateOneOptionalUserEntity().get());
		userList.add(generateOneOptionalUserEntity().get());
		
		return userList;
	}

	public List<PhoneEntity> generateOnePhoneEntityList() {
		List<PhoneEntity> listPhones = new ArrayList<>();
		
		listPhones.add(generateOptionalPhoneEntity().get());
		listPhones.add(generateOptionalPhoneEntity().get());
		
		return listPhones;
	}
	
	public SaveUserRequest generateOneSaveUserRequest(boolean withId) {
		SaveUserRequest request = new SaveUserRequest();
		String name = "Name Teste";
		String email = "email_user_request@email.com";
		String password = "123456";
		
		if ( withId ) {
			request.setId(1L);
		}
		
		request.setName(name);
		request.setEmail(email);
		request.setEmailConfirm(email);
		request.setPassword(password);
		request.setPasswordConfirm(password);
		
		return request; 
	}

	public Optional<PhoneEntity> generateOptionalPhoneEntity() {
		PhoneEntity phone = new PhoneEntity();
		
		phone.setDdd(123);
		phone.setNumber("123123");
		phone.setType("R");
		
		return Optional.of(phone);
	}
	
	private Optional<AddressEntity> generateOneOptionalAddress() {
		AddressEntity address = new AddressEntity();
		
		address.setAddressName("Some Address Name");
		
		return Optional.of(address);
	}
	
}
