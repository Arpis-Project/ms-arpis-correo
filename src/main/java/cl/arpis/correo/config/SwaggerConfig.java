package cl.arpis.correo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

/**
 * 
 * @author Stephanie
 *
 */
@Configuration
public class SwaggerConfig {

	@Value("${swagger.info.name: API Name}")
	private String apiName;
	@Value("${swagger.info.description: API Description}")
	private String apiDescription;
	@Value("${swagger.info.version: API Version}")
	private String apiVersion;
	@Value("${swagger.info.contact.name: Contact Name}")
	private String contactName;
	@Value("${swagger.info.contact.mail: contact@email.com}")
	private String contactEmail;
	
	@Bean
	OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info()
						.title(this.apiName)
						.description(this.apiDescription)
						.version(this.apiVersion)
						.contact(new Contact()
								.email(this.contactEmail)
								.name(this.contactName))
						);
	}

}
