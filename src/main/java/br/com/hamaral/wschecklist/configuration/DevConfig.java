package br.com.hamaral.wschecklist.configuration;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.hamaral.wschecklist.service.util.DatabaseService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DatabaseService databaseService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if (!"create".equals(strategy)) {
			return false;
		}
		databaseService.instantiateTestDatabase();
		return true;
	}
}
