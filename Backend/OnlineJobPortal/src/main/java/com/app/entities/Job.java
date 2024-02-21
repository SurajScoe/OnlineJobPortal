package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="joblistings")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "jobProv" ,callSuper = true)
public class Job extends BaseEntity{
	@Column(length=30)
	private String companyName;
	@Column(length=20)
	private String position;
	@Column(length=100)
	private String description;
	private int openings;
	private double renumeration;
	@Column(name="apply_link")
	private String applyLink;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="jobprovider_id")
	private JobProvider jobProv;
	
	public Job(String companyName, String position, String description, int openings, double renumeration) {
		super();
		this.companyName = companyName;
		this.position = position;
		this.description = description;
		this.openings = openings;
		this.renumeration = renumeration;
	}
	
	
	
}
