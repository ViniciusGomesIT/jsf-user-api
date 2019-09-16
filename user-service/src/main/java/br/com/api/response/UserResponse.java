package br.com.api.response;

import java.io.Serializable;
import java.util.Optional;

import br.com.api.entity.UserEntity;

public class UserResponse extends GenericResponse implements Serializable {
	
	private static final long serialVersionUID = 7329531733436852452L;
	
	private Optional<UserEntity> user = Optional.empty();

	public Optional<UserEntity> getUser() {
		return user;
	}

	public void setUser(Optional<UserEntity> user) {
		this.user = user;
	}
	
	public void setUser(UserEntity user) {
		this.user = Optional.ofNullable(user);
	}
}
