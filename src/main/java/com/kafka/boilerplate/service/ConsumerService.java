package com.kafka.boilerplate.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ConsumerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);
	private final File userSchema;

	ConsumerService() throws Exception{
		userSchema = new ClassPathResource("schema/userSchema.json").getFile();
	}

	@KafkaListener(topics = "topic-boilerplate-", groupId = "foo")
	public void listenGroupFoo(String message) {
		LOGGER.info("Received Message in group foo: " + message);
	}

	/* Spring also supports retrieval of one or more message headers using the @Header annotation in the listener: */
	//@KafkaListener(topics = "topic1, topic2", groupId = "foo") // Read multiple topics
	@KafkaListener(topics = "topic-boilerplate", groupId = "foo")
	public void listenWithHeaders(@Payload String message,
	                              @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		try {
			this.validatorJsonAgainstSchema(userSchema, message);
			LOGGER.info("Received Payload: " + message + " from partition: " + partition);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void validatorJsonAgainstSchema(final File schemaFile, final String jsonInput) throws Exception {
		// this line will generate JSON schema from your JSON FILE
		final JsonNode schemaNode = JsonLoader.fromFile(schemaFile);
		// make your JSON to JsonNode
		final JsonNode jsonToValidate =  JsonLoader.fromString(jsonInput);
		// validate it against the schema
		final ProcessingReport validate = JsonSchemaFactory.byDefault().getJsonSchema(schemaNode).validate(jsonToValidate);
		// validate.messages contains error massages
		System.out.println("Is valid Json String against Schema File? " + validate.isSuccess());
	}

	/* Consuming Messages from a Specific Partition
	@KafkaListener(topicPartitions = @TopicPartition(topic = "topicName",
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
