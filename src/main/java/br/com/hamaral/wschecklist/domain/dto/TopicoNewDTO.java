package br.com.hamaral.wschecklist.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class TopicoNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(position = 1, required = true)
	@NotBlank(message = "É obrigatório inserir o nome do Tópico!")
	private String nome;
}