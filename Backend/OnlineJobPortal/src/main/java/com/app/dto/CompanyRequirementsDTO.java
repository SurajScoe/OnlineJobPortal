package com.app.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyRequirementsDTO {
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	@NotBlank
	private String jobRole;
	@NotBlank
	private int openings;
	@NotBlank
	private double renumeration;
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long compId;
	
}


