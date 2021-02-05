package org.springframework.samples.petclinic.web;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Autor;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.service.AutorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/autor")
public class AutorControllerAlternativo {

	@Autowired
	private  AutorService autorService;
	
	
	@GetMapping("/verTodos")
	public Collection<Autor> listAutores(){
		return autorService.findAll();
	}
	
	@GetMapping("/{id}/libros")
	public Collection<Libro> verLibrosAutor(@PathVariable int id){
		return autorService.findById(id).get().getLibros();
	}
	
	@GetMapping("/{id}")
	public Autor verAutor1(@PathVariable int id){
		return autorService.findById(id).get();
	}
	
}
