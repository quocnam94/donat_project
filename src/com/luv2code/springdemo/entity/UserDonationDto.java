package com.luv2code.springdemo.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//	DTO class
public class UserDonationDto {
	
	private int donatId;
	private int userId;
	private String name;
	
	@Min(value = 1, message = "Must be greater than 0")
	@NotNull(message = "is required")
	private Double money;
	
	private String text;
	
	public UserDonationDto() {
	}
	
	public UserDonationDto(int donatId, int userId, String name, Double money, String note) {
		this.donatId = donatId;
		this.userId = userId;
		this.name = name;
		this.money = money;
		this.text = note;
	}

	public int getDonatId() {
		return donatId;
	}
	public void setDonatId(int donatId) {
		this.donatId = donatId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getText() {
		return text;
	}
	public void setText(String note) {
		this.text = note;
	}
	
}
