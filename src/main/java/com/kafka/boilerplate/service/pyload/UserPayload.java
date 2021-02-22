package com.kafka.boilerplate.service.pyload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kafka.boilerplate.controller.constant.JobPosition;
import com.kafka.boilerplate.controller.dto.Type;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties
@Validated
@Component
public class UserPayload {

	@JsonProperty(required = true)
	@NotNull
	private String name;

	private String age;

	private Type type;

	private JobPosition position;

}