package com.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.app.entities.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerDTO {
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	@NotBlank
	private String qualification;
	@NotNull
	private long phoneNo;
	@JsonProperty(access = Access.READ_ONLY)
	private String imagePath;

	private Address address;
}
