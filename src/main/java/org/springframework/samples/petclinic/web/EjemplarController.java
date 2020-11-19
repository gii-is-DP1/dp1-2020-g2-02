package org.springframework.samples.petclinic.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Ejemplar;
import org.springframework.samples.petclinic.service.EjemplarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/libros/{libroId}")
public class EjemplarController {

	
	@Autowired
	EjemplarService EjemplaresService;
	
	@GetMapping
	public String listEjemplares(ModelMap model) {
		String vista = "ejemplares/listEjemplar";
		Collection<Ejemplar> Ejemplares = EjemplaresService.findAll();
		model.addAttribute("ejemplares", Ejemplares);
		return vista;
	}
}
