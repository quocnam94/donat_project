package com.luv2code.springdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.luv2code.springdemo.validation.PhoneNumber;

@Entity
@Table(name="donat")
public class Donat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotNull(message = "is required")
	@Size(min=1, message = "is required")
	@Column(name = "code")
	private String code;
	
	@NotNull(message = "is required")
	@Size(min=1, message = "is required")
	@Column(name = "name")
	private String name;
	
	@NotNull(message = "is required")
	@Size(min=1, message = "is required")
	@Pattern(regexp = "^\\d{2}\\/\\d{2}\\/\\d{4}$", message = "must follow format dd/MM/yyyy")
	@Column(name = "start_date")
	private String startDate;
	
	@NotNull(message = "is required")
	@Size(min=1, message = "is required")
	@Pattern(regexp = "^\\d{2}\\/\\d{2}\\/\\d{4}$", message = "must follow format dd/MM/yyyy")
	@Column(name = "end_date")
	private String endDate;
	
	@NotNull(message = "is required")
	@Size(min=1, message = "is required")
	@Column(name = "organization")
	private String organization;
	
	@NotNull(message = "is required")
	@Size(min=1, message = "is required")
	@PhoneNumber
	@Pattern(regexp = "[0-9]{10}", message = "must include 10 digits")
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "money_donated")
	private Double moneyDonated;
	
	@Min(value = 1, message = "Must be greater than 0")
	@NotNull(message = "is required")
	@Column(name = "money_target")
	private Double moneyTarget;
	
	@Column(name = "status")
	private String status;

	@Column(name = "is_active")
	private boolean isActive;	
	
	public Donat() {
	}

	public Donat(String code, String name,
			String startDate,
			String endDate,
			String organization, String phoneNumber,
			String content, Double moneyDonated, Double moneyTarget, String status, boolean isActive) {
		this.code = code;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.organization = organization;
		this.phoneNumber = phoneNumber;
		this.content = content;
		this.moneyDonated = moneyDonated;
		this.moneyTarget = moneyTarget;
		this.status = status;
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Double getMoneyDonated() {
		return moneyDonated;
	}

	public void setMoneyDonated(Double moneyDonated) {
		this.moneyDonated = moneyDonated;
	}

	public Double getMoneyTarget() {
		return moneyTarget;
	}

	public void setMoneyTarget(Double moneyTarget) {
		this.moneyTarget = moneyTarget;
	}

	public String getStatus() {
		return status;
	}

	public String setStatus(String status) {
		return this.status = status;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Donat [id=" + id + ", code=" + code + ", name=" + name + ", startDate=" + startDate + ", endDate="
				+ endDate + ", organization=" + organization + ", phoneNumber=" + phoneNumber + ", content=" + content
				+ ", moneyTarget=" + moneyTarget + ", status=" + status + ", isActive=" + isActive + "]";
	}
	
}
