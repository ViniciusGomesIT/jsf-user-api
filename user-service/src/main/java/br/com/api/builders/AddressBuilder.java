package br.com.api.builders;

import br.com.api.entity.AddressEntity;
import br.com.api.entity.CityEntity;
import br.com.api.entity.CountryEntity;
import br.com.api.entity.NeighborhoodEntity;
import br.com.api.entity.StateEntity;

public class AddressBuilder {

	private String addressName;
	private String addressComplement;
	private String addressPostalCode;
	private String neighborhoodName;
	private String cityName;
	private String stateName;
	private String countryName;

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
	
	public AddressBuilder withCountry(String countryName) {
		this.countryName = countryName;	
		return this;
	}
	
	public AddressEntity build() {
		AddressEntity address = new AddressEntity();
		address.setAddress(addressName);
		address.setAddressComplement(addressComplement);
		address.setPostalCode(addressPostalCode);
		
		CountryEntity country = new CountryEntity();
		country.setName(countryName);
		
		StateEntity state = new StateEntity();
		state.setName(stateName);
		state.setCountry(country);
		
		CityEntity city = new CityEntity();
		city.setName(cityName);
		city.setState(state);
		
		NeighborhoodEntity neighborhood = new NeighborhoodEntity();
		neighborhood.setName(neighborhoodName);
		neighborhood.setCity(city);
		
		address.setNeighborhood(neighborhood);
		
		return address;
	}
}
