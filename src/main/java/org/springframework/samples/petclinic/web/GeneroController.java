package org.springframework.samples.petclinic.web;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Genero;
import org.springframework.samples.petclinic.service.GeneroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/generos")
public class GeneroController {
	
	@Autowired
	private GeneroService generoService;
	
	
	@GetMapping
	public String listGeneros(ModelMap model) {
		String vista = "generos/listGenero";
		Collection<Genero> generos = generoService.findAll();
		model.addAttribute("generos", generos);
		return vista;
	}

}
