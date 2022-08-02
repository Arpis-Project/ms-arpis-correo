package cl.arpis.correo.config;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class QueriesConfig {

	private Set<Object> set;
	private Properties prop = new Properties();

	@Bean
	public void queryProperties() throws IOException {
		this.prop.load(this.getClass().getClassLoader()
				.getResourceAsStream("queries.properties"));
		this.set = prop.keySet();
	}

	public String getQueryByName(final String query) {
		String retProp = null;
		for(Object obj : this.set) {
			if(obj.toString().equals(query)) {
				retProp = this.prop.getProperty(obj.toString());
				break;
			}
		}
		return retProp;
	}

}
