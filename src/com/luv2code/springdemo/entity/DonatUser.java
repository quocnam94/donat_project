package com.luv2code.springdemo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "donat_user")
public class DonatUser {
	
//	mapping with id from donation and user entity
	@ManyToOne(fetch = FetchType.EAGER,
			cascade = {CascadeType.DETACH, CascadeType.MERGE,
					CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "donat_id")
	private Donat theDonat;
	
	@ManyToOne(fetch = FetchType.EAGER,
			cascade = {CascadeType.DETACH, CascadeType.MERGE,
					CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	private User theUser;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Min(value = 1, message = "Must be greater than 0")
	@NotNull(message = "is required")
	@Column
	private Double money;
	
	@Column
	private String name;
	
	@Column
	private String text;

	public DonatUser() {
	}

	public DonatUser(Donat theDonat, User theUser, Double money, String name, String text) {
		this.theDonat = theDonat;
		this.theUser = theUser;
		this.money = money;
		this.name = name;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Donat getTheDonat() {
		return theDonat;
	}

	public void setTheDonat(Donat theDonat) {
		this.theDonat = theDonat;
	}

	public User getTheUser() {
		return theUser;
	}

	public void setTheUser(User theUser) {
		this.theUser = theUser;
	}

	@Override
	public String toString() {
		return "DonatUser [theDonat=" + theDonat + ", theUser=" + theUser + ", id=" + id + ", money=" + money
				+ ", name=" + name + ", text=" + text + "]";
	}
	
}
