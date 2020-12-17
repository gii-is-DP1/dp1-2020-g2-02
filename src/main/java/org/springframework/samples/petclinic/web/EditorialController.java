package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Editorial;
import org.springframework.samples.petclinic.model.Genero;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.service.EditorialService;
import org.springframework.samples.petclinic.service.LibroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/editoriales")
public class EditorialController {

	@Autowired
	private EditorialService editorialService;

	@Autowired
	private LibroService libroService;
	
	@GetMapping
	public String listEditoriales(ModelMap model) {
		String vista = "editoriales/listEditorial";
		Collection<Editorial> editoriales = editorialService.findAll();
		model.addAttribute("editoriales", editoriales);
		return vista;
	}
	
	@PostMapping(path="/save")
	public String guardarEditorial(@Valid Editorial editorial, BindingResult result, ModelMap modelmap, Principal principal) {
		String vista = "editoriales/listEditorial";
		if(result.hasErrors()) {
			modelmap.addAttribute("editorial", editorial);
			return "editoriales/editEditorial";
		}else {
			editorialService.save(editorial);
			modelmap.addAttribute("message", "Editorial guardado correctamente");
			vista = listEditoriales(modelmap);
		}
		return vista;
	}
	
	@GetMapping(path="/new")
	public String publicarEditorial(ModelMap modelmap) {
		String vista = "editoriales/editEditorial";
		modelmap.addAttribute("editorial", new Editorial());
		return vista;
	}
	
	@GetMapping(path="/{editorialId}")
	public String verLibrosAutor(@PathVariable("editorialId") int editorialId, ModelMap modelmap) {
		String vista = "editoriales/librosEditorial";
		Collection<Libro> libros = this.editorialService.getLibrosEditorial(editorialService.findById(editorialId).get());
		
		Iterator<Libro> it = libros.iterator();
		HashMap <Libro,Collection<Genero>> generosLibros = new HashMap<>();
		while(it.hasNext()) {
			Libro libro = it.next();
			generosLibros.put(libro, libroService.getGenerosLibro(libro));
		}
		modelmap.addAttribute("editorial", editorialService.findById(editorialId).get());
		modelmap.addAttribute("libros", generosLibros);
				
		return vista;
	}
	
}
