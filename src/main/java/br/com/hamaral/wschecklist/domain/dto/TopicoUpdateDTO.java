package br.com.hamaral.wschecklist.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class TopicoUpdateDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(position = 1, required = true)
	@NotBlank(message = "É obrigatório inserir o nome do Tópico!")
	private String nome;

	@ApiModelProperty(position = 2, required = true, notes = "Deve ser preenchido, sendo apenas TRUE ou FALSE.")
	@NotNull(message = "O campo FINALIZADO não pode ser null!")
	private Boolean finalizado;
}