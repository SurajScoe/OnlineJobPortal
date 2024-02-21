package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="address")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address extends BaseEntity{
	@Column(length=20)
	private String city;
	@Column(length=20)
	private String district;
	@Column(length=20)
	private String state;
	@Column(name="pincode")
	private int pinCode;
	
	public Address(String city, String district, String state, int pinCode) {
		super();
		this.city = city;
		this.district = district;
		this.state = state;
		this.pinCode = pinCode;
	}
	
	
}
