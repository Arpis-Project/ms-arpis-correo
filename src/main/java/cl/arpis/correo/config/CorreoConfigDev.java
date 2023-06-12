package cl.arpis.correo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@Profile("dev")
@PropertySources({
	@PropertySource("classpath:mail.properties"),
	@PropertySource("classpath:db-dev.properties")
})
public class CorreoConfigDev {

}
