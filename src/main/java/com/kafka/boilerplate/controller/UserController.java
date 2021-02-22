package com.kafka.boilerplate.controller;

import com.kafka.boilerplate.controller.dto.UserDTO;
import com.kafka.boilerplate.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private ProducerService producerService;

	@PostMapping("/json")
	public ResponseEntity<Void> newEmployee(@Validated @RequestBody String bodyJson) {
		producerService.sendMessageJSON(bodyJson);
		return new ResponseEntity(null, HttpStatus.CREATED);
	}


	@RequestMapping(value = "/obj", method = RequestMethod.POST)
	public ResponseEntity<Void> newEmployee(@Validated @RequestBody final UserDTO userDTO) {
		System.out.println(userDTO);
		producerService.sendMessageOBJ(userDTO);
		return new ResponseEntity(null, HttpStatus.CREATED);
	}
}
