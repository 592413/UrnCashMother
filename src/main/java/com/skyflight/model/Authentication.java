package com.skyflight.model;

public class Authentication {
	private String LoginId;
	private String Password;

	public Authentication(String LoginId, String Password) {
		this.LoginId = LoginId;
		this.Password = Password;
	}

	public String getLoginId() {
		return LoginId;
	}

	public void setLoginId(String loginId) {
		LoginId = loginId;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	@Override
	public String toString() {
		return "Authentication [LoginId=" + LoginId + ", Password=" + Password + "]";
	}
	
	

}
