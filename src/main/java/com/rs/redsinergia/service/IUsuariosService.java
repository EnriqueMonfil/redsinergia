package com.rs.redsinergia.service;

import java.util.List;

import com.rs.redsinergia.model.Usuario;


public interface IUsuariosService {
		void save(Usuario usuario);
		void delete(Integer idUsuario);
	    List<Usuario> buscarTodas();
	    Usuario buscarPorUserName(String username);
}
