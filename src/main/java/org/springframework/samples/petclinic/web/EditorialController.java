package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Editorial;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.service.EditorialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/editoriales")
public class EditorialController {

	@Autowired
	private EditorialService editorialService;
	
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
			log.warn("Datos de la editorial incorrectos " + result.getAllErrors());
			return "editoriales/editEditorial";
		}else {
			editorialService.save(editorial);
			modelmap.addAttribute("message", "Editorial guardada correctamente");
			vista = listEditoriales(modelmap);
			log.info("Editorial con id: " + editorial.getId() + " guardada correctamente");
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
		
		modelmap.addAttribute("editorial", editorialService.findById(editorialId).get());
		modelmap.addAttribute("libros", libros);
				
		return vista;
	}
	
}
