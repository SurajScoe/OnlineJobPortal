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

@Table(name="company_requirements")
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "comp",callSuper = true)
public class CompanyRequirement extends BaseEntity{
	
	@Column(name="job_role",length=20)
	private String jobRole;
	private int openings;
	private double renumeration;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="company_id")
	private Company comp;

	public CompanyRequirement(String jobRole, int openings, double renumeration) {
		super();
		this.jobRole = jobRole;
		this.openings = openings;
		this.renumeration = renumeration;
	}
	
	
}
