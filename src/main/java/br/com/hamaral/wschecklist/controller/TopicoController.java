package br.com.hamaral.wschecklist.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.hamaral.wschecklist.controller.exception.StandardError;
import br.com.hamaral.wschecklist.domain.Topico;
import br.com.hamaral.wschecklist.domain.dto.TopicoNewDTO;
import br.com.hamaral.wschecklist.domain.dto.TopicoUpdateDTO;
import br.com.hamaral.wschecklist.service.TopicoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Topico", value = "Topico")
@RestController
@RequestMapping(path = "/api/v1/topico")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Topico>> findAll() {
        return ResponseEntity.ok().body(topicoService.findAll());
    }

    @ApiResponses(value = {@ApiResponse(message = "Not Found", code = 404, response = StandardError.class)})
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Topico> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(topicoService.findById(id));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/status")
    public ResponseEntity<List<Topico>> findByFinalizado(@RequestParam Boolean finalizado) {
        return ResponseEntity.ok().body(topicoService.findByFinalizado(finalizado));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody TopicoNewDTO topicoNewDTO) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(topicoService.insert(topicoNewDTO).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Void> updateById(@Valid @RequestBody TopicoUpdateDTO topicoUpdateDTO,
                                           @PathVariable Integer id) {
        topicoService.updateById(topicoUpdateDTO, id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        topicoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
