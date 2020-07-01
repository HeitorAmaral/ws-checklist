package br.com.hamaral.wschecklist.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import br.com.hamaral.wschecklist.service.util.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.hamaral.wschecklist.domain.Topico;
import br.com.hamaral.wschecklist.domain.dto.TopicoNewDTO;
import br.com.hamaral.wschecklist.domain.dto.TopicoUpdateDTO;
import br.com.hamaral.wschecklist.exception.DataIntegrityException;
import br.com.hamaral.wschecklist.exception.GenericException;
import br.com.hamaral.wschecklist.exception.ObjectNotFoundException;
import br.com.hamaral.wschecklist.repository.TopicoRepository;
import br.com.hamaral.wschecklist.service.util.ExceptionBuilderService;
import br.com.hamaral.wschecklist.service.validation.TopicoValidationService;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TopicoValidationService topicoValidation;

    @Autowired
    private ConverterService converterService;

    @Autowired
    private ExceptionBuilderService exceptionBuilderService;

    public List<Topico> findAll() {
        try {
            return topicoRepository.findAll();
        } catch (Exception e) {
            throw new GenericException(exceptionBuilderService.getGenericMethodExceptionForNoneParameter("Topico",
                    "findAll", e.toString()));
        }
    }

    public Topico findById(Integer id) {
        try {
            return topicoRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException(exceptionBuilderService
                    .getValidationExceptionForNotFoundRegister("Topico", "topicoId", id.toString()));
        } catch (Exception e) {
            String[] parameters = {id.toString()};
            throw new GenericException(exceptionBuilderService.getGenericMethodExceptionForMultiParameters("Topico",
                    "findById", parameters, e.toString()));
        }
    }

    public Optional<Topico> findByIdForValidation(Integer id) {
        try {
            return topicoRepository.findById(id);
        } catch (Exception e) {
            String[] parameters = {id.toString()};
            throw new GenericException(exceptionBuilderService.getGenericMethodExceptionForMultiParameters("Topico",
                    "findByIdForValidation", parameters, e.toString()));
        }
    }

    public Optional<Topico> findByNome(String nome) {
        try {
            return topicoRepository.findByNome(nome);
        } catch (Exception e) {
            String[] parameters = {nome};
            throw new GenericException(exceptionBuilderService.getGenericMethodExceptionForMultiParameters("Topico",
                    "findByNome", parameters, e.toString()));
        }
    }

    public List<Topico> findByFinalizado(Boolean finalizado) {
        try {
            if (finalizado) {
                return topicoRepository.findByFinalizado();
            } else {
                return topicoRepository.findByNotFinalizado();
            }

        } catch (Exception e) {
            String[] parameters = {finalizado.toString()};
            throw new GenericException(exceptionBuilderService.getGenericMethodExceptionForMultiParameters("Topico",
                    "findByFinalizado", parameters, e.toString()));
        }
    }

    public Topico insert(TopicoNewDTO topicoNewDTO) {
        topicoValidation.validateNewTopico(topicoNewDTO);
        return save(new Topico(topicoNewDTO.getNome(), topicoNewDTO.getDataCriacao()));
    }

    public void updateById(TopicoUpdateDTO topicoUpdateDTO, Integer id) {
        Topico topico = new Topico();
        topico = findById(id);

        topicoValidation.validateUpdateTopico(topicoUpdateDTO, id);

        Boolean edited = false;

        if (topico.getNome() != topicoUpdateDTO.getNome()) {
            topico.setNome(topicoUpdateDTO.getNome());
            edited = true;
        }

        if (converterService.formatDate(topico.getDataCriacao()) != topicoUpdateDTO.getDataCriacao()) {
            topico.setDataCriacao(converterService.stringToDate(topicoUpdateDTO.getDataCriacao()));
            edited = true;
        }

        if (converterService.formatDate(topico.getDataConclusao()) != topicoUpdateDTO.getDataConclusao()) {
            topico.setDataConclusao(converterService.stringToDate(topicoUpdateDTO.getDataConclusao()));
            edited = true;
        }

        if (edited) {
            save(topico);
        }
    }

    public Topico save(Topico topico) {
        try {
            return topicoRepository.save(topico);
        } catch (DataIntegrityViolationException e) {
            String[] parameters = {"topico"};
            throw new DataIntegrityException(
                    exceptionBuilderService.getIntegrityViolationMethodExceptionForMultiParameters("Topico", "save",
                            parameters, e.getMostSpecificCause().toString()));
        } catch (Exception e) {
            String[] parameters = {"topico"};
            throw new GenericException(exceptionBuilderService.getGenericMethodExceptionForMultiParameters("Topico",
                    "save", parameters, e.toString()));
        }
    }

    public void deleteById(Integer id) {
        findById(id);
        try {
            topicoRepository.deleteById(id);
        } catch (Exception e) {
            String[] parameters = {id.toString()};
            throw new GenericException(exceptionBuilderService.getGenericMethodExceptionForMultiParameters("Tópico",
                    "deleteById", parameters, e.toString()));
        }
    }
}