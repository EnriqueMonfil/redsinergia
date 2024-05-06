package com.rs.redsinergia.db;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.redsinergia.model.Perfil;
import com.rs.redsinergia.model.Usuario;
import com.rs.redsinergia.repository.UsuariosRepository;
import com.rs.redsinergia.service.IUsuariosService;


@Service
public class UsuariosServiceJpa implements IUsuariosService {
	@Autowired
	private UsuariosRepository  usuariosRepository;
	
	@Override
	public void save(Usuario usuario) {
		usuario.setEstatus(1);
		usuario.setFechaRegistro(new Date());
		
		Perfil perfil1 = new Perfil();
		perfil1.setId(2);
		perfil1.setPerfil("ADMINISTRADOR");
		List<Perfil> listaPerfil = new LinkedList<>();
		listaPerfil.add(perfil1);
		usuario.setPerfiles(listaPerfil);
		usuariosRepository.save(usuario);
	}

	@Override
	public void delete(Integer idUsuario) {
		usuariosRepository.deleteById(idUsuario);

	}

	@Override
	public List<Usuario> buscarTodas() {
		return usuariosRepository.findAll();
	}

	@Override
	public Usuario buscarPorUserName(String username) {
		// TODO Auto-generated method stub
		return usuariosRepository.findByUsername(username);
	}

}
