package br.com.api.response;

import java.util.List;

import br.com.api.entity.UserEntity;

public class UserListResponse extends GenericResponse {

	private List<UserEntity> userList;

	public List<UserEntity> getUserList() {
		return userList;
	}

	public void setUserList(List<UserEntity> userList) {
		this.userList = userList;
	}

}
