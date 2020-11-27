package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Autor;
import org.springframework.samples.petclinic.service.AutorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AutorController {

	@Autowired
	private AutorService autorService;
	
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
}
