package com.luv2code.springdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.luv2code.springdemo.validation.PhoneNumber;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotNull(message = "is required")
	@Size(min=1, message = "is required")
	@Column(name = "full_name")
	private String fullName;
	
	@NotNull(message = "is required")
	@Size(min=1, message = "is required")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9+_.-]+\\.[A-Za-z0-9-]{2,253}$", message = "email invalid")
	@Column(name = "email")
	private String email;
	
	@NotNull(message = "is required")
	@Size(min=1, message = "is required")
	@PhoneNumber
	@Pattern(regexp = "[0-9]{10}", message = "must include 10 digits")
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@NotNull(message = "is required")
	@Size(min=1, message = "is required")
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "role_id")
	private String roleId;
	
	@NotNull(message = "is required")
	@Size(min=1, message = "is required")
	@Column(name = "address")
	private String address;
	
	@NotNull(message = "is required")
	@Size(min=1, message = "is required")
	@Column(name = "password")
	private String password;
	
	@Column(name = "status")
	private String status;
		
	public User() {
	}

	public User(String fullName,
			String email,
			String phoneNumber, String userName,
			String roleId, String address, String password, String status) {
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.roleId = roleId;
		this.address = address;
		this.password = password;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public String setStatus(String status) {
		return this.status = status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullName=" + fullName + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", userName=" + userName + ", roleId=" + roleId + ", address=" + address + ", password=" + password
				+ ", status=" + status + "]";
	}
	
}
