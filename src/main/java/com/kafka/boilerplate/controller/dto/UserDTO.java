package com.kafka.boilerplate.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kafka.boilerplate.controller.constant.JobPosition;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
	@NotEmpty(message = "Name is required")
	@NotNull
	private String name;

	@NotBlank(message = "Not blank")
	private String age;

	private Type type;

	private JobPosition position;
}