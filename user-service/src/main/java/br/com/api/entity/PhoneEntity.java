package br.com.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.api.enums.PhoneTypeEnum;

@Entity(name = "tbg_phone")
public class PhoneEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "ddd", nullable = false)
	private Integer ddd;

	@Column(name = "numero", nullable = false, unique = true)
	private String number;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = false)
	private PhoneTypeEnum type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public PhoneTypeEnum getType() {
		return type;
	}

	public void setType(PhoneTypeEnum type) {
		this.type = type;
	}

}
