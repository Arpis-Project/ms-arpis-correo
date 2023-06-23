package cl.arpis.correo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

/**
 * 
 * @author steph
 *
 */
@Configuration
@PropertySource("classpath:queries.properties")
public class ApplicationConfig {

	@Bean
	ITemplateResolver templateResolver() {
		StringTemplateResolver templateResolver = new StringTemplateResolver();
		templateResolver.setOrder(1);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCacheable(false);
		return templateResolver;
	}

}
