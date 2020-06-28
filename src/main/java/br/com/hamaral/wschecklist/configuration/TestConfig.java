package br.com.hamaral.wschecklist.configuration;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.hamaral.wschecklist.service.util.DatabaseService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DatabaseService databaseService;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		databaseService.instantiateTestDatabase();
		return true;
	}
}
