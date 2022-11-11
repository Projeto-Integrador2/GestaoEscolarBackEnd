package br.com.jgsolutions.gems.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "aluno")
@Data
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String nomeAluno;

    private String cpfAluno;
    private String emailAluno;
    private String telefoneAluno;

    @Column(length = 4000)
    private String observacaoAluno;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

}
