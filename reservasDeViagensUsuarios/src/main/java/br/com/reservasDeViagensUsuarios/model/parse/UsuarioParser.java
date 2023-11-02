package br.com.reservasDeViagensUsuarios.model.parse;

import br.com.reservasDeViagensUsuarios.model.dto.UsuarioDTO;
import br.com.reservasDeViagensUsuarios.model.entity.Usuario;
import jakarta.validation.Valid;

public class UsuarioParser {

	public static Usuario toUsuarioEntity(@Valid UsuarioDTO usuario) {
		Usuario usuarioEntity = new Usuario();
		return usuarioEntity;
	}

	public static UsuarioDTO toUsuarioDTO(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		return usuarioDTO;
	}

}
