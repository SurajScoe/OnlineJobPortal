package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddressDTO {
	@JsonProperty(access = Access.READ_ONLY)
	private Long Id;
	
	private String city;
	
	private String district;
	
	private String state;
	
	private String pinCode;		
}