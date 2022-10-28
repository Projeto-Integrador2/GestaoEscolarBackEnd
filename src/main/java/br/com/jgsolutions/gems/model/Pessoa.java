package br.com.jgsolutions.gems.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;

@Entity
@Table(name = "pessoa")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
public class Pessoa implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nomePessoa;
	
	@NotBlank
	private String cpfPessoa;
	
	@NotBlank
	private Date dataNascPessoa;
	
	@NotBlank
	private String emailPessoa;
	
	@NotBlank
	private String telefonePessoa;
}
