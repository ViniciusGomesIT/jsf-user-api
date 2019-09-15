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
@Table(name = "tbg_cidade")
public class CityEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String name;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinTable(name = "tbg_cidade_estado", 
		joinColumns = @JoinColumn(name = "cidade_id"),
		inverseJoinColumns = @JoinColumn(name = "estado_id"))
	private StateEntity state;
	
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

	public StateEntity getState() {
		return state;
	}

	public void setState(StateEntity state) {
		this.state = state;
	}

}
