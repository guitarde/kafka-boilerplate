package com.kafka.boilerplate.controller;

import com.kafka.boilerplate.dto.User;
import com.kafka.boilerplate.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private ProducerService producerService;

	@PostMapping("/json")
	void newEmployee(@RequestBody String bodyJson) {
		//producerService.sendMessageJSON(bodyJson);
	}

	@PostMapping("/obj")
	void newEmployee(@Validated @Valid @RequestBody User user) {
		System.out.println(user);
		//producerService.sendMessageOBJ(user);
	}
}
