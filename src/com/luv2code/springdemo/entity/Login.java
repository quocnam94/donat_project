package com.luv2code.springdemo.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Login {
	@NotNull(message = "is required")
	@Size(min=1, message = "is required")
	private String email;
	
	@NotNull(message = "is required")
	@Size(min=1, message = "is required")
	private String password;
	
	private String roleId;
	private String status;
	
	public Login() {
	}

	public Login (String email,String password) {
		this.email = email;
		this.password = password;
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

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
