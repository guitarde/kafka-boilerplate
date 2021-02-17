package com.kafka.boilerplate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.boilerplate.dto.User;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ConsumerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

	@KafkaListener(topics = "topic-boilerplate-", groupId = "foo")
	public void listenGroupFoo(String message) {
		LOGGER.info("Received Message in group foo: " + message);
	}

	/* Spring also supports retrieval of one or more message headers using the @Header annotation in the listener: */
	//@KafkaListener(topics = "topic1, topic2", groupId = "foo") // Read multiple topics
	//@Valid
	@KafkaListener(topics = "topic-boilerplate", groupId = "foo")
	public void listenWithHeaders(@Payload String message,
	                              @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		final ObjectMapper objectMapper = new ObjectMapper();

		try {
			final User user = objectMapper.readValue(message, User.class);
			LOGGER.info("Received Payload: " + user + "from partition: " + partition);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	/* Consuming Messages from a Specific Partition */
	/* @KafkaListener(topicPartitions = @TopicPartition(topic = "topicName",
			partitionOffsets = {
				@PartitionOffset(partition = "0", initialOffset = "0"),
				@PartitionOffset(partition = "3", initialOffset = "0")}),
		containerFactory = "partitionsKafkaListenerContainerFactory")
	public void listenToPartition(
		@Payload String message,
		@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		LOGGER.info("Received Message: " + message + "from partition: " + partition);
	}*/
}
