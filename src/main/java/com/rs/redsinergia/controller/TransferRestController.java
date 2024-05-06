package com.rs.redsinergia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.redsinergia.model.transacciones;
import com.rs.redsinergia.service.ITransaccionService;

@RestController
@RequestMapping("/api")
public class TransferRestController {
	
	@Autowired
	private ITransaccionService transaccionesService;
	
	@PostMapping("/newTransfer")
	public transacciones guardar(@RequestBody transacciones tr) {
		transaccionesService.guardar(tr);
		return tr;
	}

}
