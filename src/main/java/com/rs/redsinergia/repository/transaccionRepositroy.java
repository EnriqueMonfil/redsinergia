package com.rs.redsinergia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rs.redsinergia.model.Usuario;
import com.rs.redsinergia.model.transacciones;

public interface transaccionRepositroy extends JpaRepository<transacciones, Integer> {
	/**@Query("SELECT c FROM transacciones c WHERE c.idUsuario= :idUsuario")**/
	/**List<transacciones> findByIdUsuario(Integer idUsuario);**/
}
