package cl.arpis.correo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
	@PropertySource("classpath:mail.properties"),
	@PropertySource("classpath:db-prod.properties")
})
@Profile("prod")
public class CorreoConfigProd {

}
