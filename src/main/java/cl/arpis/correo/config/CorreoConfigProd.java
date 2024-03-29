package cl.arpis.correo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@Profile("prod")
@PropertySources({
	@PropertySource("classpath:mail-prod.properties"),
	@PropertySource("classpath:db-prod.properties")
})
public class CorreoConfigProd {

}
