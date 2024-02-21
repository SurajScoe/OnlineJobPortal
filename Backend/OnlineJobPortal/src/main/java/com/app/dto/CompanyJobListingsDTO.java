package com.app.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class CompanyJobListingsDTO {
	private Long id;
	@NotBlank
	private String companyName;
	@NotBlank
	private String companyEmail;
	@NotBlank
	private String password;
	private List<CompanyRequirementsDTO> companyRequirements;

}
