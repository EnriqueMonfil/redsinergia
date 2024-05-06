package com.rs.redsinergia.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.rs.redsinergia.model.Cuenta;
import com.rs.redsinergia.model.transacciones;
import com.rs.redsinergia.service.ITransaccionService;
import com.rs.redsinergia.service.ITransaccionServiceJDBC;

@Service
public class TransassionService implements ITransaccionServiceJDBC{
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<transacciones> obtenerTransaccionesPorIdUsuario(Integer idUsuario) {
		   return jdbcTemplate.query(
		            "SELECT idUsuario, cuentaOrigen, cuentaDestino, fecha, cantidad FROM transacciones where idUsuario="+idUsuario,
		            (rs, rowNum) -> new transacciones(
		                rs.getInt("idUsuario"),
		                rs.getInt("cuentaOrigen"),
		                rs.getInt("cuentaDestino"),
		                rs.getDate("fecha"),
		                rs.getDouble("cantidad")
		            )
		        );
	}

	@Override
	public List<Cuenta> obtenerListaDeCuentasUsuario(Integer idUsuario) {
		 return jdbcTemplate.query(
		            "SELECT idUsuario, numeroCuentaOrigen, saldo FROM cuentasOrigen where idUsuario="+idUsuario,
		            (rs, rowNum) -> new Cuenta(
		                rs.getInt("idUsuario"),
		                rs.getInt("numeroCuentaOrigen"),
		                rs.getInt("saldo")
		             
		            )
		        );	}

  

}
