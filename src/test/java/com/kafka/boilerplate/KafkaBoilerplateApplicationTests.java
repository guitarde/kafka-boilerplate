package com.kafka.boilerplate;

import com.github.fge.jsonschema.core.load.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.ValidationException;
import javax.xml.validation.Schema;

@SpringBootTest
class KafkaBoilerplateApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void givenValidInput_whenValidating_thenValid() throws ValidationException {

	}
}
