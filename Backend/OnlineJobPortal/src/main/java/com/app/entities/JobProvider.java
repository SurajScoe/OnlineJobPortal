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
@Table(name="jobproviders")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"joblist","password"})
public class JobProvider extends BaseEntity{
	@Column(name="jobprovider_name",length=20)
	private String jobProviderName;
	@Column(name="jobprovider_email",length=30)
	private String jobProviderEmail;
	private String password;
	@OneToMany(mappedBy="jobProv",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Job> joblist=new ArrayList<Job>();
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	
	
	public JobProvider(String jobProviderName, String jobProviderEmail, String password) {
		super();
		this.jobProviderName = jobProviderName;
		this.jobProviderEmail = jobProviderEmail;
		this.password = password;
	}
	
	//helper methods
	public void addJob(Job j) {
		joblist.add(j);
		j.setJobProv(this);
	}
	
	public  void deleteJob(Job j) {
		joblist.remove(j);
		j.setJobProv(null);
	}
}
