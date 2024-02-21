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
public class JobProviderDto {
	@JsonProperty(access =Access.READ_ONLY)
	private Long id;
	@NotBlank
	private String jobProviderName;
	@NotBlank
	private String jobProviderEmail;
	@NotBlank
	private String password;
	private Address address;
}
