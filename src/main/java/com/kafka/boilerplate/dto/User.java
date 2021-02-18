package com.kafka.boilerplate.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class User {
	@NotEmpty(message = "Name is required")
	private String name;

	private String age;
	@NotBlank
	private Type type;
}

