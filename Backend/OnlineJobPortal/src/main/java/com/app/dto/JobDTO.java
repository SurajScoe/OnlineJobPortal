package com.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JobDTO {
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	@NotBlank
	private String companyName;	
	@NotBlank
	private String position;
	@NotBlank
	private String description;
	@NotNull
	private int openings;
	@NotNull
	private double renumeration;
	@NotBlank
	private String applyLink;
	@JsonProperty(access=Access.WRITE_ONLY)
	private Long jobProvId;
}
