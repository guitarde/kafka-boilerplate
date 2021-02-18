package com.kafka.boilerplate.controller;

import com.kafka.boilerplate.dto.User;
import com.kafka.boilerplate.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private ProducerService producerService;

	@PostMapping("/json")
	void newEmployee(@Validated @RequestBody String bodyJson) {
		//producerService.sendMessageJSON(bodyJson);
	}


	@RequestMapping(value = "/obj", method = RequestMethod.POST)
	public ResponseEntity<Void> newEmployee(@Validated @RequestBody final User user) {
		System.out.println(user);
		return new ResponseEntity(null, HttpStatus.CREATED);		//producerService.sendMessageOBJ(user);
	}
}
