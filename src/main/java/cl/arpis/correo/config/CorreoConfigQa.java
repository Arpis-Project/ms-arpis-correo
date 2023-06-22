package cl.arpis.correo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@Profile("qa")
@PropertySources({
	@PropertySource("classpath:mail-qa.properties"),
	@PropertySource("classpath:db-qa.properties")
})
public class CorreoConfigQa {

}
