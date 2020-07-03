package br.com.hamaral.wschecklist.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
public class TopicoNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(position = 1, required = true)
    @NotBlank(message = "É obrigatório inserir o nome do Tópico!")
    private String nome;

    @ApiModelProperty(position = 2, required = true)
    @NotBlank(message = "É obrigatório inserir a Data de Criação!")
    @Pattern(regexp = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$", message = "É obrigatório inserir a Data no padrão DD/MM/AAAA")
    private String dataCriacao;
}