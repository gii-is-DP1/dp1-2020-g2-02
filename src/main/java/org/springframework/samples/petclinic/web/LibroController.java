package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.LibroService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
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

	private static final String VIEWS_LIBROS_CREATE_OR_UPDATE_FORM = "libros/createOrUpdateLibroForm";
	
	@Autowired
	LibroService librosService;

	@GetMapping
	public String listLibros(ModelMap model) {
		String vista = "libros/listLibro";
		Collection<Libro> Libros = librosService.findAll();
		model.addAttribute("libros", Libros);
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
	/*
	@GetMapping(value = "/pets/{petId}/edit")
	public String initUpdateForm(@PathVariable("libroId") int libroId, ModelMap model) {
		Optional<Libro> libro = this.librosService.findById(libroId);
		model.put("libro", libro);
		return VIEWS_LIBROS_CREATE_OR_UPDATE_FORM;
	}
	
    @PostMapping(value = "/pets/{petId}/edit")
	public String processUpdateForm(@Valid Libro libro, BindingResult result,@PathVariable("libroId") int libroId, ModelMap model) {
		if (result.hasErrors()) {
			model.put("libro", libro);
			return VIEWS_LIBROS_CREATE_OR_UPDATE_FORM;
		}
		else {
                        Optional<Libro> libroToUpdate=this.librosService.findById(libroId);
			BeanUtils.copyProperties(libro, libroToUpdate, "id","owner","visits");                                                                                  
                    try {                    
                        this.librosService.save(libroToUpdate);                    
                    } catch (DuplicatedPetNameException ex) {
                        result.rejectValue("name", "duplicate", "already exists");
                        return VIEWS_LIBROS_CREATE_OR_UPDATE_FORM;
                    }
			return "redirect:/owners/{ownerId}";
		}
	}*/
}
