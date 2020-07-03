package br.com.hamaral.wschecklist.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import br.com.hamaral.wschecklist.service.util.ConverterService;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class Topico implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(position = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(position = 2)
    @Column(nullable = false)
    private String nome;

    @ApiModelProperty(position = 4)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "Brazil/East")
    private Date dataCriacao;

    @ApiModelProperty(position = 5)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "Brazil/East")
    private Date dataConclusao;

    public Topico(String nome, String dataCriacao) {
        ConverterService converterService = new ConverterService();
        this.nome = nome;
        this.dataCriacao = converterService.stringToDate(dataCriacao);
    }
}
