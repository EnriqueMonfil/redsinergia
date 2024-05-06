package com.rs.redsinergia.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.redsinergia.model.transacciones;
import com.rs.redsinergia.repository.transaccionRepositroy;
import com.rs.redsinergia.service.ITransaccionService;
@Service
public class TransaccionServiceJpa implements ITransaccionService{
	
	@Autowired
	transaccionRepositroy transRepositroy;
	
	public List<transacciones> buscarPorIdUser(Integer idUsuario) {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(idUsuario);
		List<transacciones> transac = transRepositroy.findAllById(ids);

		if(!transac.isEmpty()) {
			return transac;
		}
		
		return null;
	}

	@Override
	public void guardar(transacciones transfer) {
		transRepositroy.save(transfer);
		
	}

}
