package br.com.hamaral.wschecklist.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" }, callSuper = false)
public class Topico implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(position = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ApiModelProperty(position = 2)
	@Column(nullable = false)
	private String nome;

	@ApiModelProperty(position = 3)
	@Column(nullable = false)
	private Boolean finalizado;

	public Topico(String nome) {
		this.nome = nome;
		this.finalizado = false;
	}
}
