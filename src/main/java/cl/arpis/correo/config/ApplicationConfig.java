package cl.arpis.correo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
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

	@Bean
	ITemplateEngine templateEngine() {
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}

}
