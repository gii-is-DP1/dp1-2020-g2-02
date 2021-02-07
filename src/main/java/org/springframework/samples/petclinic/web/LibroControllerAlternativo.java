package org.springframework.samples.petclinic.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.service.LibroService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/libros")
public class LibroControllerAlternativo {
	
	@Autowired
	LibroService libroService;
	
	
	@RequestMapping(value="/get", method = RequestMethod.GET)
	public @ResponseBody Collection<Libro> getLibros(){
		return libroService.findAll();
	}
	
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET)
	public @ResponseBody Libro getLibro(@PathVariable int id){
		return libroService.findById(id).get();
	}
	
	@GetMapping(path="")
	public String listLibros() {
		return "libros/librosAPI";
	}
	
	@GetMapping(path="/{id}")
	public String listLibro(@PathVariable int id) {
		return "libros/librosAPI";
	}

}
