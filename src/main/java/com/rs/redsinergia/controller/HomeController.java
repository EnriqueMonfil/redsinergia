package com.rs.redsinergia.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.redsinergia.model.ReporteTransaccionesPorUsuarioHome;
import com.rs.redsinergia.model.Usuario;
import com.rs.redsinergia.model.transacciones;
import com.rs.redsinergia.service.ITransaccionService;
import com.rs.redsinergia.service.ITransaccionServiceJDBC;
import com.rs.redsinergia.service.IUsuariosService;
import com.rs.redsinergia.utilerias.PieChartExample;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.aspectj.util.FileUtil;
import org.jfree.*;
import org.jfree.chart.ChartUtils;

import java.io.File;

@Controller
public class HomeController {
	
	@Autowired
	private IUsuariosService serviceUsuarios;
	@Autowired
	private ITransaccionService transaccionesService;
	@Autowired
	private ITransaccionServiceJDBC transaccionServiceJDBC;
	@Autowired
	private ResourceLoader resourceLoader;
	@GetMapping("/")
	public String home(Authentication auth, HttpSession session, Model model) {
		String username = auth.getName();
     

		 // Mapa para almacenar los resultados
        Map<String, ReporteTransaccionesPorUsuarioHome> resumenMap = new HashMap<>();
        
        //si no hay usuario logueado
		//if(session.getAttribute("usuario") == null) {
			//busca usuario usando JPA
			Usuario user = serviceUsuarios.buscarPorUserName(username);
			user.setPassword(null);
			session.setAttribute("usuario", user);
			
			//obtiene las transacciones del usuario usando JDBC
			List<transacciones> tra =  transaccionServiceJDBC.obtenerTransaccionesPorIdUsuario(user.getId());
			List<Integer> listaCuentasDestino = new ArrayList<>();

		
		    // Iterar sobre las transacciones y obtener reporte	         	
	         for (transacciones transaccion : tra) {
				listaCuentasDestino.add(transaccion.getCuentaDestino());
				
	            int cuentaOrigen = transaccion.getCuentaOrigen();
	            double monto = transaccion.getCantidad();
	            Date fecha = transaccion.getFecha();

	            // Si ya hay una entrada en el mapa para esta cuenta origen, actualizar los valores
	            if (resumenMap.containsKey(String.valueOf(cuentaOrigen))) {
	            	ReporteTransaccionesPorUsuarioHome resumen = resumenMap.get(String.valueOf(cuentaOrigen));
	                resumen.setMonto(resumen.getMonto() + monto);
	                if (fecha.compareTo(resumen.getFecha()) > 0) {
	                    resumen.setFecha(fecha);
	                }
	            } else {
	                // Si no hay una entrada en el mapa para esta cuenta origen, crear una nueva entrada
	                resumenMap.put(String.valueOf(cuentaOrigen), new ReporteTransaccionesPorUsuarioHome(cuentaOrigen, fecha, monto));
	            }
	        }
	        
	        // Convertir el mapa  en una lista
	        List<ReporteTransaccionesPorUsuarioHome> resumenTransacciones = new ArrayList<>(resumenMap.values());

	        
			model.addAttribute("listaBalance", resumenTransacciones);
			
			
			 // Crear el gráfico de pastel
	        PieChartExample chart = new PieChartExample("Gráfico de Pastel: Cuenta Destino", listaCuentasDestino);
	      
	        // Generar imagen de gráfico y guardarla
	        try {
				String rutaImagen = resourceLoader.getResource("classpath:static/images/").getFile().getPath();
				generarImagenDeGrafico(chart, rutaImagen+"grafico_pastel3.png");
				File imageFile = new File(rutaImagen+"grafico_pastel3.png");
	            byte[] imageData = FileUtil.readAsByteArray(imageFile);
	            String base64Image = java.util.Base64.getEncoder().encodeToString(imageData);
	            model.addAttribute("imagen", base64Image);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        

		//}

		return "home";
	}
	
	@GetMapping("/index")
	public String index(Authentication auth) {
		String username = auth.getName();
		System.out.println("el usuario es:"+username);
		return "redirect:/";
	}
	
	
	   public static void generarImagenDeGrafico(PieChartExample chart, String filePath) throws IOException {
	        File pieChartImage = new File(filePath);
	        ChartUtils.saveChartAsPNG(pieChartImage, chart.getChart(), 800, 600);
	    }
	
	
}
