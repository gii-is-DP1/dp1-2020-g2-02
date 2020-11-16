package org.springframework.samples.petclinic.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.service.LibroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/libros")
public class LibroController {

	@Autowired
	LibroService LibrosService;

	@GetMapping
	public String listLibros(ModelMap model) {
		String vista = "libros/listLibro";
		Collection<Libro> Libros = LibrosService.findAll();
		model.addAttribute("libros", Libros);
		return vista;
	}
	
}
