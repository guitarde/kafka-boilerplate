package com.kafka.boilerplate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.boilerplate.controller.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProducerService.class);

	//@Value("${contactInfoType}")
	private final String topic = "";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessageJSON(final String msgJSON) {
		LOGGER.info("Send message like JSON: " + msgJSON);
		kafkaTemplate.send("topic-boilerplate", msgJSON);
	}

	public void sendMessageOBJ(final UserDTO userDTO) {
		final ObjectMapper objectMapper = new ObjectMapper();
		try {
			//TODO: Find option to show string with object
			LOGGER.info("Send message like OBJ: " + userDTO);
			kafkaTemplate.send("topic-boilerplate", objectMapper.writeValueAsString(userDTO));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
