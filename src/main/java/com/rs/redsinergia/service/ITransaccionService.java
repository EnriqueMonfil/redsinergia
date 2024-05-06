package com.rs.redsinergia.service;

import java.util.List;
import java.util.Optional;

import com.rs.redsinergia.model.Usuario;
import com.rs.redsinergia.model.transacciones;

public interface ITransaccionService {
	List<transacciones> buscarPorIdUser(Integer idUsuario);
	void guardar(transacciones transfer);

}
