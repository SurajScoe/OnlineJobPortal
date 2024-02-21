package com.app.dto;

import javax.validation.constraints.NotBlank;

import com.app.entities.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CompanyDTO {
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	@NotBlank
	private String companyName;
	@NotBlank
	private String companyEmail;
	@NotBlank
	private String password;
	
	private Address address;
}
