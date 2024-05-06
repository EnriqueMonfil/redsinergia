package com.rs.redsinergia.service;

import java.util.List;

import com.rs.redsinergia.model.Cuenta;
import com.rs.redsinergia.model.transacciones;

public interface ITransaccionServiceJDBC {
	List<transacciones> obtenerTransaccionesPorIdUsuario(Integer idUsuario);
    List<Cuenta> obtenerListaDeCuentasUsuario(Integer idUsuario);

}
