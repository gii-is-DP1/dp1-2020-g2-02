package org.springframework.samples.petclinic.web;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Autor;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.service.AutorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/autor")
public class AutorControllerAlternativo {

	@Autowired
	private  AutorService autorService;
	
	
	@RequestMapping(value="/verTodos", method = RequestMethod.GET)
	public @ResponseBody Collection<Autor> listAutores(){
		return autorService.findAll();
	}
	
	@RequestMapping(value="/{id}/libros", method = RequestMethod.GET)
	public @ResponseBody Collection<Libro> verLibrosAutor(@PathVariable int id){
		return autorService.findById(id).get().getLibros();
	}

	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public @ResponseBody Autor verAutor(@PathVariable int id){
		return autorService.findById(id).get();
	}
	
	@GetMapping(path="/ver")
	public String verAutor() {
		return "index";
	}
}
