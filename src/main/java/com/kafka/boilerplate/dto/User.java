package com.kafka.boilerplate.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class User {
	@NotNull(message = "Name is required")
	private String name;
	@Min(value = 1, message ="Age should be min 1")
	private String age;
	@NotNull
	private Type type;
	@NotNull
	private String title;

}

