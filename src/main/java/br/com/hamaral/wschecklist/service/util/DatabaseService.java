package br.com.hamaral.wschecklist.service.util;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

	@Autowired
	private ConverterService converterService;

	public void instantiateTestDatabase() throws ParseException {

	}
}
