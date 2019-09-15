package br.com.api.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
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
	private String address;

	@Column(name = "complemento")
	private String addressComplement;

	@Column(name = "cep", nullable = false)
	private String postalCode;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinTable(name = "tbg_endereco_bairro", 
		joinColumns = @JoinColumn(name = "endereco_id"), 
		inverseJoinColumns = @JoinColumn(name = "bairro_id"))
	private NeighborhoodEntity neighborhood;
	
	@PrePersist
	@PreUpdate
	private void setAddressAndComplementUpperCased() {
		this.address = address.toUpperCase();
		this.addressComplement = addressComplement.toUpperCase();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public NeighborhoodEntity getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(NeighborhoodEntity neighborhood) {
		this.neighborhood = neighborhood;
	}

}
