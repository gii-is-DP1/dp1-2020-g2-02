package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Genero;
import org.springframework.samples.petclinic.service.GeneroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	
	@PostMapping(path="/save")
	public String guardarGenero(@Valid Genero genero, BindingResult result, ModelMap modelmap, Principal principal) {
		String vista = "generos/listGenero";
		if(result.hasErrors()) {
			modelmap.addAttribute("genero", genero);
			modelmap.addAttribute("message", "Hay fallos en el formulario.");
			return "generos/editLibro";
		}else {
			generoService.save(genero);
			modelmap.addAttribute("message", "Género guardado correctamente");
			vista = listGeneros(modelmap);
		}
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearGenero(ModelMap modelmap) {
		String vista = "generos/editGenero";
		modelmap.addAttribute("genero", new Genero());
		return vista;
	}	

}
