package br.com.reservasDeViagensUsuarios.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reservasDeViagensUsuarios.exception.UsuarioException;
import br.com.reservasDeViagensUsuarios.model.dto.UsuarioDTO;
import br.com.reservasDeViagensUsuarios.model.parse.UsuarioParser;
import br.com.reservasDeViagensUsuarios.repository.UsuarioRepository;
import br.com.reservasDeViagensUsuarios.service.UsuarioService;
import jakarta.validation.Valid;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository repository;

	@Override
	public UsuarioDTO create(@Valid UsuarioDTO usuario) {
		
		
		UsuarioDTO usuarioDTO = UsuarioParser.toUsuarioDTO(repository.save(UsuarioParser.toUsuarioEntity(usuario)));
		
			
		
		return usuarioDTO;
	}

	@Override
	public UsuarioDTO findById(Long id) {
		return UsuarioParser.toUsuarioDTO(
				repository.findById(id).orElseThrow(() -> new UsuarioException("Não foi possivel buscar a usuario.")));
	}

}
