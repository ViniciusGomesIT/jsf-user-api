package br.com.api.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.api.entity.PhoneEntity;

public class SaveUserRequest extends GenericRequest implements Serializable {

	private static final long serialVersionUID = 998384118088456493L;

	private Long id;

	private String name;

	private String email;
	private String emailConfirm;

	private String password;
	private String passwordConfirm;

	private boolean renderPassword;
	private boolean renderReset;

	private Date dateOfBirth;
	private Date registrationDate;
	private String gender;
	private String maritalStatus;

	private Integer phone1DDD;
	private String phone1Number;
	private String phone1Type;

	private Integer phone2DDD;
	private String phone2Number;
	private String phone2Type;

	private String addressName;
	private String addressComplement;
	private String addressPostalCode;
	private String neighborhoodName;
	private String cityName;
	private String stateName;
	private String countryName;

	private List<PhoneEntity> phones;

	public SaveUserRequest() {
		renderPassword = true;
		renderReset = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailConfirm() {
		return emailConfirm;
	}

	public void setEmailConfirm(String emailConfirm) {
		this.emailConfirm = emailConfirm;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public boolean isRenderPassword() {
		return renderPassword;
	}

	public void setRenderPassword(boolean renderPassword) {
		this.renderPassword = renderPassword;
	}

	public boolean isRenderReset() {
		return renderReset;
	}

	public void setRenderReset(boolean renderReset) {
		this.renderReset = renderReset;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Integer getPhone1DDD() {
		return phone1DDD;
	}

	public void setPhone1DDD(Integer phone1ddd) {
		phone1DDD = phone1ddd;
	}

	public String getPhone1Number() {
		return phone1Number;
	}

	public void setPhone1Number(String phone1Number) {
		this.phone1Number = phone1Number;
	}

	public String getPhone1Type() {
		return phone1Type;
	}

	public void setPhone1Type(String phone1Type) {
		this.phone1Type = phone1Type;
	}

	public Integer getPhone2DDD() {
		return phone2DDD;
	}

	public void setPhone2DDD(Integer phone2ddd) {
		phone2DDD = phone2ddd;
	}

	public String getPhone2Number() {
		return phone2Number;
	}

	public void setPhone2Number(String phone2Number) {
		this.phone2Number = phone2Number;
	}

	public String getPhone2Type() {
		return phone2Type;
	}

	public void setPhone2Type(String phone2Type) {
		this.phone2Type = phone2Type;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getAddressComplement() {
		return addressComplement;
	}

	public void setAddressComplement(String addressComplement) {
		this.addressComplement = addressComplement;
	}

	public String getAddressPostalCode() {
		return addressPostalCode;
	}

	public void setAddressPostalCode(String addressPostalCode) {
		this.addressPostalCode = addressPostalCode;
	}

	public String getNeighborhoodName() {
		return neighborhoodName;
	}

	public void setNeighborhoodName(String neighborhoodName) {
		this.neighborhoodName = neighborhoodName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public List<PhoneEntity> getPhones() {
		if (null == this.phones) {
			this.phones = new ArrayList<>();
		}

		return phones;
	}

	public void setPhones(List<PhoneEntity> phones) {
		this.phones = phones;
	}

}
