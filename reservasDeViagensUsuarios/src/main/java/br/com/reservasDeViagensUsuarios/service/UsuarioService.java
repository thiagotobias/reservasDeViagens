package br.com.reservasDeViagensUsuarios.service;

import br.com.reservasDeViagensUsuarios.model.dto.UsuarioDTO;

public interface UsuarioService {

	UsuarioDTO create(UsuarioDTO usuario);

	UsuarioDTO findById(Long id);

}
