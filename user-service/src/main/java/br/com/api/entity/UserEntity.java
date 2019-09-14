package br.com.api.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "tbg_usuario")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@Column(name = "nome")
	private String name;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "senha", nullable = false)
	private String password;
	
	@Column(name = "ultimo_acesso")
	@Temporal(TemporalType.DATE)
	private Date lastAcess;
	
	@Column(name = "data_registro")
	@Temporal(TemporalType.DATE)
	private Date registrationDate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "tbg_usuario_telefones", 
		joinColumns = @JoinColumn(name = "usuario_id"), 
		inverseJoinColumns = @JoinColumn(name = "telefone_id"))
	private List<PhoneEntity> phones;
	
	@PrePersist
	@PreUpdate
	private void setNameAndEmailUpperCase() {
		this.email = email.toUpperCase();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getLastAcess() {
		return lastAcess;
	}

	public void setLastAcess(Date lastAcess) {
		this.lastAcess = lastAcess;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public List<PhoneEntity> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneEntity> phones) {
		this.phones = phones;
	}

}
