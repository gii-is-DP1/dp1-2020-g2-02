package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Genero;
import org.springframework.samples.petclinic.model.Libro;
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

	@GetMapping
	public String listLibros(ModelMap model) {
		String vista = "libros/listLibro";
		Map<Libro, Collection<Genero>> map = new HashMap<Libro, Collection<Genero>>();
		Iterator<Libro> it = librosService.findAll().iterator();
		while (it.hasNext()) {
			Libro libro = it.next();
			map.put(libro, librosService.getGenerosLibro(libro));
			System.out.println("AquiController " + librosService.getGenerosLibro(libro).size());
		}
		model.addAttribute("libros", map);
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
	
	@GetMapping(path="/delete/{libroId}")
	public String borrarLibro(@PathVariable("libroId") int libroId, ModelMap modelmap) {
		String vista = "libros/listLibro"; 
		Optional<Libro> libro = librosService.findById(libroId);
		if(libro.isPresent()) {
			librosService.delete(libro.get());
			modelmap.addAttribute("message", "Libro eliminado correctamente");
		}else {
			modelmap.addAttribute("message", "Libro no encontrado");
		}
		vista = listLibros(modelmap);
		return vista;
	}
	
}
