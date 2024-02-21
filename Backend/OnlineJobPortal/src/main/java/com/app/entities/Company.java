package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="companies")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"password","compsreq"})
public class Company extends BaseEntity{
	@Column(name="company_name",length=50)
	private String companyName;
	@Column(name="company_email",length=50)
	private String companyEmail;
	@Column(length=30)
	private String password;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	@OneToMany(mappedBy = "comp",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
	private List<CompanyRequirement> compsreq=new ArrayList<CompanyRequirement>();
	

	//helper methods
	public void addRequirement(CompanyRequirement creq) {
		compsreq.add(creq);
		creq.setComp(this);
	}
	
	public void removeReqirement(CompanyRequirement creq) {
		compsreq.remove(creq);
		creq.setComp(null);
	}

	public Company(String companyName, String companyEmail, String password) {
		super();
		this.companyName = companyName;
		this.companyEmail = companyEmail;
		this.password = password;
	}
	
	
}
