package br.com.api.builders;

import java.util.Date;
import java.util.List;

import br.com.api.entity.PhoneEntity;
import br.com.api.request.SaveUserRequest;

public class SaveUserRequestBuilder {
	
	private String name;
	
	private String password;
	private String passwordConfirm;

	private String email;
	private String emailConfirm;

	private Date registrationDate;
	private String gender;
	private String maritalStatus;
	private Date dateOfBirth;

	private String addressName;
	private String addressComplement;
	private String addressPostalCode;
	private String neighborhoodName;
	private String cityName;
	private String stateName;

	private List<PhoneEntity> phones;
	
	public SaveUserRequestBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public SaveUserRequestBuilder withEmail(String email) {
		this.email = email;
		return this;
	}
	
	public SaveUserRequestBuilder withEmailConfirm(String emailConfirm) {
		this.emailConfirm = emailConfirm;
		return this;
	}
	
	public SaveUserRequestBuilder withPassword(String password) {
		this.password = password;
		return this;
	}
	
	public SaveUserRequestBuilder withPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
		return this;
	}
	
	public SaveUserRequestBuilder withRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
		return this;
	}
	
	public SaveUserRequestBuilder withGender(String gender) {
		this.gender = gender;
		return this;
	}
	
	public SaveUserRequestBuilder withMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
		return this;
	}

	public SaveUserRequestBuilder withDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		return this;
	}
	
	public SaveUserRequestBuilder withAddressName(String addressName) {
		this.addressName = addressName;
		return this;
	}
	
	public SaveUserRequestBuilder withAddressComplement(String addressComplement) {
		this.addressComplement = addressComplement;
		return this;
	}
	
	public SaveUserRequestBuilder withAddressPostalCode(String addressPostalCode) {
		this.addressPostalCode = addressPostalCode;
		return this;
	}
	
	public SaveUserRequestBuilder withNeighborhoodName(String neighborhoodName) {
		this.neighborhoodName = neighborhoodName;
		return this;
	}
	
	public SaveUserRequestBuilder withCityName(String cityName) {
		this.cityName = cityName;
		return this;
	}
	
	public SaveUserRequestBuilder withStateName(String stateName) {
		this.stateName = stateName;
		return this;
	}
	
	public SaveUserRequestBuilder withPhones(List<PhoneEntity> phones) {
		this.phones = phones;
		return this;
	}
	
	public SaveUserRequest build() {
		SaveUserRequest request = new SaveUserRequest();
		
		request.setName(name);
		request.setEmail(email);
		request.setEmailConfirm(emailConfirm);
		request.setPassword(password);
		request.setPasswordConfirm(passwordConfirm);
		request.setRegistrationDate(registrationDate);
		request.setGender(gender);
		request.setMaritalStatus(maritalStatus);
		request.setDateOfBirth(dateOfBirth);
		request.setAddressName(addressName);
		request.setAddressComplement(addressComplement);
		request.setAddressPostalCode(addressPostalCode);
		request.setNeighborhoodName(neighborhoodName);
		request.setCityName(cityName);
		request.setStateName(stateName);
		request.setPhones(phones);
		
		return request;
	}
}
