package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Autor;
import org.springframework.samples.petclinic.model.Genero;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.service.AutorService;
import org.springframework.samples.petclinic.service.LibroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/autores")
public class AutorController {

	@Autowired
	private AutorService autorService;

	@Autowired
	private LibroService libroService;
	
	@GetMapping
	public String listAutores(ModelMap model) {
		String vista = "autores/listAutor";
		Collection<Autor> autores = autorService.findAll();
		model.addAttribute("autores", autores);
		return vista;
	}
	
	@PostMapping(path="/save")
	public String guardarAutor(@Valid Autor autor, BindingResult result, ModelMap modelmap, Principal principal) {
		String vista = "autores/listAutor";
		if(result.hasErrors()) {
			modelmap.addAttribute("autor", autor);
			return "autores/editAutor";
		}else {
			autorService.save(autor);
			modelmap.addAttribute("message", "Autor guardado correctamente");
			vista = listAutores(modelmap);
		}
		return vista;
	}
	
	@GetMapping(path="/new")
	public String publicarAutor(ModelMap modelmap) {
		String vista = "autores/editAutor";
		modelmap.addAttribute("autor", new Autor());
		return vista;
	}
	
	@GetMapping(path="/{autorId}")
	public ModelAndView verLibrosAutor(@PathVariable("autorId") int autorId) {
		ModelAndView mav = new ModelAndView("autores/librosAutor");
		Autor autor = this.autorService.findById(autorId).get();
		mav.addObject("autor", autor);
		Iterator<Libro> libros = autor.getLibros().iterator();
		
		mav.addObject("libros", libros);
		return mav;
	}
}
