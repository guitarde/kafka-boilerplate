package validatorSchemaJson;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;

public class ValidatorWithSchema {

	static File schemaFile = new File("src/main/java/validatorSchemaJson/resource/schema.json");
	static File jsonFile = new File("src/main/java/validatorSchemaJson/resource/data.json");

	static String jsonInput = "{\n" +
		"  \"id\": 1,\n" +
		"  \"name\": \"A green door\",\n" +
		"  \"price\": 12.50,\n" +
		"  \"tags\": [\"home\", \"green\"],\n" +
		"  \"message\": \"A message\"\n" +
		"}";

	public static void main(String[] args) throws Exception {
		validatorJsonAgainstSchema(schemaFile, jsonFile);
		validatorJsonAgainstSchema(schemaFile, jsonInput);
	}

	private static void validatorJsonAgainstSchema(final File schemaFile, final File jsonFile) throws Exception {
		// this line will generate JSON schema from your JSON FILE
		JsonNode schemaNode = JsonLoader.fromFile(schemaFile);
		// make your JSON to JsonNode
		JsonNode jsonToValidate =  JsonLoader.fromFile(jsonFile);
		// validate it against the schema
		ProcessingReport validate = JsonSchemaFactory.byDefault().getJsonSchema(schemaNode).validate(jsonToValidate);
		// validate.messages contains error massages
		System.out.println("Is valid Json File against Schema File? : " + validate.isSuccess());
	}

	private static void validatorJsonAgainstSchema(final File schemaFile, final String jsonInput) throws Exception {
		// this line will generate JSON schema from your JSON FILE
		JsonNode schemaNode = JsonLoader.fromFile(schemaFile);
		// make your JSON to JsonNode
		JsonNode jsonToValidate =  JsonLoader.fromString(jsonInput);
		// validate it against the schema
		ProcessingReport validate = JsonSchemaFactory.byDefault().getJsonSchema(schemaNode).validate(jsonToValidate);
		// validate.messages contains error massages
		System.out.println("Is valid Json String against Schema File? " + validate.isSuccess());
	}
}