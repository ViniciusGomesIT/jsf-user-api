package br.com.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "tbg_endereco")
public class AddressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "endereco", nullable = false)
	private String addressName;

	@Column(name = "complemento")
	private String addressComplement;

	@Column(name = "cep", nullable = false)
	private String postalCode;
	
	@Column(name = "bairro", nullable = false)
	private String neighborhoodName;

	@Column(name = "cidade", nullable = false)
	private String cityName;

	@Column(name = "estado", nullable = false)
	private String stateName;

	@PrePersist
	@PreUpdate
	private void setAddressComplementCityStateNeighboorhoodUpperCased() {
		this.addressName = addressName.toUpperCase();
		this.addressComplement = addressComplement.toUpperCase();
		this.neighborhoodName = neighborhoodName.toUpperCase();
		this.cityName = cityName.toUpperCase();
		this.stateName = stateName.toUpperCase();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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

}
