package br.com.reservasDeViagensUsuarios.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
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

}
