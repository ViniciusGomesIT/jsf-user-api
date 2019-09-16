package br.com.api.builders;

import br.com.api.entity.AddressEntity;


public class AddressBuilder {

	private String addressName;
	private String addressComplement;
	private String addressPostalCode;
	private String neighborhoodName;
	private String cityName;
	private String stateName;

	public AddressBuilder withAddressName(String addressName) {
		this.addressName = addressName;
		return this;
	}
	
	public AddressBuilder withAddressComplement(String addressComplement) {
		this.addressComplement = addressComplement;
		return this;
	}
	
	public AddressBuilder withAddressPostalCode(String addressPostalCode) {
		this.addressPostalCode = addressPostalCode;
		return this;
	}
	
	public AddressBuilder withNeighborhood(String neighborhoodName) {
		this.neighborhoodName = neighborhoodName;
		return this;
	}
	
	public AddressBuilder withCity(String cityName) {
		this.cityName = cityName;
		return this;
	}
	
	public AddressBuilder withState(String stateName) {
		this.stateName = stateName;
		
		return this;
	}
	
	public AddressEntity build() {
		
		AddressEntity address = new AddressEntity();
		address.setAddressName(addressName);
		address.setAddressComplement(addressComplement);
		address.setPostalCode(addressPostalCode);
		address.setNeighborhoodName(neighborhoodName);
		address.setCityName(cityName);
		address.setStateName(stateName);
		
		return address;
	}
}
