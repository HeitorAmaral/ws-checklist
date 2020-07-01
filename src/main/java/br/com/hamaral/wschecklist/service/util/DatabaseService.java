package br.com.hamaral.wschecklist.service.util;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hamaral.wschecklist.domain.Topico;
import br.com.hamaral.wschecklist.repository.TopicoRepository;

@Service
public class DatabaseService {

    @Autowired
    private TopicoRepository topicoRepository;

    public void instantiateTestDatabase() throws ParseException {

        Topico topico01 = new Topico("PI", "30/06/2020 21:00:00");
        Topico topico02 = new Topico("Trabalho de Inglês", "30/06/2020 21:00:00");

        topicoRepository.saveAll(Arrays.asList(topico01, topico02));

    }
}
