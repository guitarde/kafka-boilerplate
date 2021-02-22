package validatorSchemaJson;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class main {
	public static void main( String[] args ) throws IOException, ProcessingException {
		File schemaFile = new File("src/main/java/validatorSchemaJson/resource/schema.json");
		File jsonFile = new File("src/main/java/validatorSchemaJson/resource/data.json");

		if (ValidationUtils.isJsonValid(schemaFile, jsonFile)){
			System.out.println("Valid!");
		}else{
			System.out.println("NOT valid!");
		}
	}
}
