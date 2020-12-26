package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Autor;
import org.springframework.samples.petclinic.model.Ejemplar;
import org.springframework.samples.petclinic.model.Genero;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.service.EjemplarService;
import org.springframework.samples.petclinic.service.LibroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/libros")
public class LibroController {

	
	@Autowired
	LibroService librosService;
	
	@Autowired
	EjemplarService ejemplarService;

	@GetMapping
	public String listLibros(ModelMap model) {
		String vista = "libros/listLibro";
		Map<Integer, Collection<Genero>> mapGeneros = new HashMap<Integer, Collection<Genero>>();
		Map<Integer, Collection<Autor>> mapAutores = new HashMap<Integer, Collection<Autor>>();
		Map<Integer, Boolean> disponibilidad = new HashMap<Integer, Boolean>();
		Collection<Libro> libros = librosService.findAll();
		Iterator<Libro> it = libros.iterator();
		
		while (it.hasNext()) {
			Libro libro = it.next();
			Integer id = libro.getId();
			mapGeneros.put(id, librosService.getGenerosLibro(libro));
			mapAutores.put(id, librosService.getAutoresLibro(libro));
			disponibilidad.put(id, !ejemplarService.findDisponibles(libro).isEmpty());
			//System.out.println("AquiController " + librosService.getAutoresLibro(libro).size());
		}
		model.addAttribute("libros",libros);
		model.addAttribute("librosGeneros", mapGeneros);
		model.addAttribute("librosAutores", mapAutores);
		model.addAttribute("disponibilidad", disponibilidad);
		
		return vista;
	}
	
	@GetMapping(path="/reservar/{libroId}")
	public String reservar(@PathVariable("libroId") int libroId, ModelMap model) {
		Optional<Libro> libro = librosService.findById(libroId);
		if(!libro.isPresent()) {
			model.addAttribute("message","Libro no existente");
			String vista = listLibros(model);
			return vista;
		}
		Collection<Ejemplar> ejemplaresDisponibles = ejemplarService.findDisponibles(libro.get());
		model.addAttribute("message","Libro existente");
		String vista = listLibros(model);
		return vista;
	}
	@PostMapping(path="/save")
	public String guardarLibro(@Valid Libro libro, BindingResult result, ModelMap modelmap) {
		String vista = "libros/listLibro";
		if(result.hasErrors()) {
			modelmap.addAttribute("libro", libro);
			return "libros/editLibro";
		}else {
			librosService.save(libro);
			modelmap.addAttribute("message", "Libro guardado correctamente");
			vista = listLibros(modelmap);
		}
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearLibro(ModelMap modelmap) {
		String vista = "libros/editLibro";
		modelmap.addAttribute("libro", new Libro());
		return vista;
	}
	
}
