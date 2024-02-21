package com.app.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="jobseeker")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"password"})
public class JobSeeker extends BaseEntity{
	@Column(name="first_name",length=20)
	private String firstName;
	@Column(name="last_name",length=20)
	private String lastName;
	@Column(name="email",length=50)
	private String email;
	@Column(length=20)
	private String password;
	private String qualification;
	private long phoneNo;
	private String imagePath;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	
	public JobSeeker(String firstName, String lastName, String email, String password, String qualification,
			int phoneNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.qualification = qualification;
		this.phoneNo = phoneNo;
	}
	
}
