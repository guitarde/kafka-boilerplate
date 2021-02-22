package com.kafka.boilerplate.controller.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JobPosition {

	ADMIN("ADMIN", 1),
	USER("USER", 2),
	TECHNICAL("TECHNICAL", 3);

	JobPosition(String position, int i) {	}
}
