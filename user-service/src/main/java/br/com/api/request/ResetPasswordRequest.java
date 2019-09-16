package br.com.api.request;

import java.io.Serializable;

public class ResetPasswordRequest extends GenericRequest implements Serializable {

	private static final long serialVersionUID = -7735217741550561387L;
	
	private Long id;
	private String oldPassword;
	private String newPassword;
	private String newPasswordConfirm;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}

}
