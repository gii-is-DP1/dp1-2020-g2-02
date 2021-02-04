package org.springframework.samples.petclinic.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.DatosDiarios;
import org.springframework.samples.petclinic.service.DatosDiariosService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/estadisticas")
public class EstadisticasController {

	@Autowired
	private DatosDiariosService datosService;
	
	@GetMapping
	public String listEstadisticas(ModelMap model) {
		String vista = "datosDiarios/listDatos";
		Collection<DatosDiarios> datosDiarios = datosService.findAllOrderByFecha();
		model.addAttribute("datosDiarios", datosDiarios);
		return vista;
	}
}
