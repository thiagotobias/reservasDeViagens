package br.com.reservasDeViagensUsuarios.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.reservasDeViagensUsuarios.model.dto.UsuarioDTO;
import br.com.reservasDeViagensUsuarios.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService service;

	@PostMapping
	public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO pessoa) {
		UsuarioDTO createdUsuario = service.create(pessoa);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUsuario.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> get(@PathVariable(name = "id") Long id) {
    	UsuarioDTO pessoa = service.findById(id);		
        return ResponseEntity.ok(pessoa);
    }

}
