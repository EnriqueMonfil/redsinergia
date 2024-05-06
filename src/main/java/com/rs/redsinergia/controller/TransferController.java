package com.rs.redsinergia.controller;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rs.redsinergia.model.Cuenta;
import com.rs.redsinergia.model.Usuario;
import com.rs.redsinergia.model.transacciones;
import com.rs.redsinergia.service.ITransaccionServiceJDBC;
import com.rs.redsinergia.service.IUsuariosService;


@Controller
@RequestMapping(value="/transferencias")
public class TransferController {
	@Autowired
	private IUsuariosService serviceUsuarios;
	@Autowired
	private ITransaccionServiceJDBC transaccionServiceJDBC;
	
	@GetMapping("/mostrar")
	public String transferencia(Authentication auth, HttpSession session, Model model) {
		String username = auth.getName();
		//busca usuario usando JPA
		Usuario user = serviceUsuarios.buscarPorUserName(username);
        
      //obtiene las transacciones y cuentas del usuario usando JDBC
		List<transacciones> listaTransacciones =  transaccionServiceJDBC.obtenerTransaccionesPorIdUsuario(user.getId());
        List<Cuenta> listaCuentasUsuario = transaccionServiceJDBC.obtenerListaDeCuentasUsuario(user.getId());

        // Mapa para almacenar las transacciones por cuenta de usuario
        Map<Cuenta, List<transacciones>> transaccionesPorCuenta = new HashMap();

        // Agrupar transacciones por cuenta de usuario
        for (Cuenta cuenta : listaCuentasUsuario) {
            List<transacciones> transaccionesDeCuenta = listaTransacciones.stream()
                    .filter(transaccion -> transaccion.getCuentaOrigen().equals(cuenta.getNumeroCuentaOrigen()))
                    .collect(Collectors.toList());
            transaccionesPorCuenta.put(cuenta, transaccionesDeCuenta);
        }

        model.addAttribute("transaccionesPorCuenta", transaccionesPorCuenta);
		return"transferencia/transfer";
	}
}
