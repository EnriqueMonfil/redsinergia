package com.rs.redsinergia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rs.redsinergia.model.Usuario;



public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
	Usuario findByUsername(String username);
}
