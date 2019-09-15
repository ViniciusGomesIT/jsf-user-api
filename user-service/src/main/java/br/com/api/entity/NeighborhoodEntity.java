package br.com.api.entity;

import javax.persistence.Table;
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

@Entity
@Table(name = "tbg_neighborhood")
public class NeighborhoodEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String name;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinTable(name = "tbg_bairro_cidade",
		joinColumns = @JoinColumn(name = "neighborhood_id"),
		inverseJoinColumns = @JoinColumn(name = "city_id"))
	private CityEntity city;
	
	@PrePersist
	@PreUpdate
	private void setNameUpperCased() {
		this.name = name.toUpperCase();
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

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
	}

}
