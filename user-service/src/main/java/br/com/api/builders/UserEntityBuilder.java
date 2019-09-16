package br.com.api.builders;

import java.util.Date;
import java.util.List;

import br.com.api.entity.AddressEntity;
import br.com.api.entity.PhoneEntity;
import br.com.api.entity.UserEntity;

public class UserEntityBuilder {
	
	private String name;
	private String email;
	private String password;
	private Date registrationDate;
	private String gender;
	private String maritalStatus;
	private Date dateOfBirth;
	private AddressEntity address;
	private List<PhoneEntity> phones;
	
	public UserEntityBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public UserEntityBuilder withEmail(String email) {
		this.email = email;
		return this;
	}
	
	public UserEntityBuilder withPassword(String password) {
		this.password = password;
		return this;
	}
	
	public UserEntityBuilder withRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
		return this;
	}
	
	public UserEntityBuilder withGender(String gender) {
		this.gender = gender;
		return this;
	}
	
	public UserEntityBuilder withMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
		return this;
	}

	public UserEntityBuilder withDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		return this;
	}
	
	public UserEntityBuilder withAddress(AddressEntity address) {
		this.address = address;
		return this;
	}
	
	public UserEntityBuilder withPhones(List<PhoneEntity> phones) {
		this.phones = phones;
		return this;
	}
	
	public UserEntity build() {
		UserEntity user = new UserEntity();
		
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setRegistrationDate(registrationDate);
		user.setGender(gender);
		user.setMaritalStatus(maritalStatus);
		user.setDateOfBirth(dateOfBirth);
		user.setAddress(address);
		user.setPhones(phones);
		
		return user;
	}
}
