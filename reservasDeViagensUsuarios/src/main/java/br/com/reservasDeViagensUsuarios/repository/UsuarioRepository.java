package br.com.reservasDeViagensUsuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.reservasDeViagensUsuarios.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
