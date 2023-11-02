package br.com.reservasDeViagensUsuarios.model.entity;

import java.time.LocalDate;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("PF")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String site;
	private String rua;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String cep;
	private String estado;
	private String cpf;
	private String rg;
	private LocalDate dataRg;
	private String orgaoRg;
	private String sexo;
	private String raca;
	private String naturalidade;
	private String nacionalidade;
	private String nomePai;
	private String nomeMae;

}
