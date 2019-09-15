package br.com.api.builders;

import br.com.api.entity.PhoneEntity;

public class PhoneBuilder {

	private Integer ddd;
	private String number;
	private String type;
	
	public PhoneBuilder withDdd(Integer ddd) {
		this.ddd = ddd;
		return this;
	}
	
	public PhoneBuilder withNumber(String number) {
		this.number = number;
		return this;
	}
	
	public PhoneBuilder withType(String type) {
		this.type = type;
		return this;
	}
	
	public PhoneEntity build() {
		PhoneEntity phoneEntity = new PhoneEntity();
		
		phoneEntity.setDdd(ddd);
		phoneEntity.setNumber(number);
		phoneEntity.setType(type);
		
		return phoneEntity;
	}
}
