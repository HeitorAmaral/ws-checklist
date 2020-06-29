package br.com.hamaral.wschecklist.service.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hamaral.wschecklist.domain.Topico;
import br.com.hamaral.wschecklist.domain.dto.TopicoNewDTO;
import br.com.hamaral.wschecklist.domain.dto.TopicoUpdateDTO;
import br.com.hamaral.wschecklist.service.TopicoService;
import br.com.hamaral.wschecklist.service.util.ExceptionBuilderService;
import br.com.hamaral.wschecklist.service.validation.util.Validator;
import br.com.hamaral.wschecklist.service.validation.util.ValidatorReturn;

@Service
public class TopicoValidationService {

	@Autowired
	private TopicoService topicoService;

	@Autowired
	private ExceptionBuilderService exceptionBuilderService;

	protected ValidatorReturn validateTopicoById(Integer topicoId) {
		Optional<Topico> optTopico = topicoService.findByIdForValidation(topicoId);
		if (optTopico.isPresent()) {
			return new ValidatorReturn();
		} else {
			return new ValidatorReturn(exceptionBuilderService.getValidationExceptionForNotFoundRegister("Topico",
					"topicoId", topicoId.toString()));
		}
	}

	private ValidatorReturn validateTopicoByNome(String nome, Integer id) {
		Optional<Topico> optTopico = topicoService.findByNome(nome);
		if (id == 0) {
			if (optTopico.isPresent()) {
				return new ValidatorReturn(
						exceptionBuilderService.getValidationExceptionForMultiRegister("Topico", "nome", nome));
			} else {
				return new ValidatorReturn();
			}
		} else {
			if (optTopico.isPresent() && !optTopico.get().getId().equals(id)) {
				return new ValidatorReturn(
						exceptionBuilderService.getValidationExceptionForMultiRegister("Topico", "nome", nome));
			} else {
				return new ValidatorReturn();
			}
		}
	}

	public void validateNewTopico(TopicoNewDTO topicoNewDTO) {
		Validator validator = new Validator();
		validator.validateOne(validateTopicoByNome(topicoNewDTO.getNome(), 0));
		validator.validateResult(validator);
	}

	public void validateUpdateTopico(TopicoUpdateDTO topicoUpdateDTO, Integer id) {
		Validator validator = new Validator();
		validator.validateOne(validateTopicoByNome(topicoUpdateDTO.getNome(), id));
		validator.validateResult(validator);
	}

}
