package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.BibliotecarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.samples.petclinic.model.Bibliotecario;

@Controller
@RequestMapping("/bibliotecarios")
public class BibliotecarioController {
	
	@Autowired
	BibliotecarioService bibliotecariosService;
	
	@GetMapping
	public String listBibliotecarios(ModelMap model) {
		String vista = "bibliotecarios/listBibliotecario";
		Collection<Bibliotecario> bibliotecarios = bibliotecariosService.findAll();
		model.addAttribute("bibliotecarios", bibliotecarios);
		return vista;
	}
	
	@PostMapping(path="/save")
	public String guardarBibliotecario(@Valid Bibliotecario bibliotecario, BindingResult result, ModelMap modelmap) {
		String vista = "bibliotecarios/listBibliotecario";
		if(result.hasErrors()) {
			modelmap.addAttribute("bibliotecario", bibliotecario);
			return "bibliotecarios/editBibliotecario";
		}else {
			bibliotecariosService.save(bibliotecario);
			modelmap.addAttribute("message", "Bibliotecario guardado correctamente");
		}
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearBibliotecario(ModelMap modelmap) {
		String vista = "bibliotecarios/editBibliotecario";
		modelmap.addAttribute("bibliotecario", new Bibliotecario());
		return vista;
	}
	
	@GetMapping(path="/delete/{bibliotecarioId}")
	public String borrarBibliotecario(@PathVariable("bibliotecarioId") int bibliotecarioId, ModelMap modelmap) {
		String vista = "bibliotecarios/listBibliotecario"; 
		Optional<Bibliotecario> bibliotecario = bibliotecariosService.findById(bibliotecarioId);
		if(bibliotecario.isPresent()) {
			bibliotecariosService.delete(bibliotecario.get());
			modelmap.addAttribute("message", "Bibliotecario eliminado correctamente");
		}else {
			modelmap.addAttribute("message", "Bibliotecario no encontrado");
		}
		return vista;
	}
	
	
	
	
	

	


	/*public static final String BIBLIOTECARIOS_FORM="bibliotecarios/createOrUpdateBibliotecarioForm";
	public static final String BIBLIOTECARIOS_LISTING="bibliotecarios/BibliotecariosListing";

	@Autowired
	BibliotecarioService bibliotecariosService;

	@GetMapping
	public String listBibliotecarios(ModelMap model)
	{
		model.addAttribute("bibliotecarios",bibliotecariosService.findAll());
		return BIBLIOTECARIOS_LISTING;
	}

	@GetMapping("/{id}/edit")
	public String editBibliotecario(@PathVariable("id") int id,ModelMap model) {
		Optional<Bibliotecario> bibliotecario=bibliotecariosService.findById(id);
		if(bibliotecario.isPresent()) {
			model.addAttribute("bibliotecario",bibliotecario.get());
			return BIBLIOTECARIOS_FORM;
		}else {
			model.addAttribute("message","We cannot find the bibliotecario you tried to edit!");
			return listBibliotecarios(model);
		}
	}

	@PostMapping("/{id}/edit")
	public String editBibliotecario(@PathVariable("id") int id, @Valid Bibliotecario modifiedBibliotecario, BindingResult binding, ModelMap model) {
		Optional<Bibliotecario> bibliotecario=bibliotecariosService.findById(id);
		if(binding.hasErrors()) {			
			return BIBLIOTECARIOS_FORM;
		}else {
			BeanUtils.copyProperties(modifiedBibliotecario, bibliotecario.get(), "id");
			bibliotecariosService.save(bibliotecario.get());
			model.addAttribute("message","Bibliotecario updated succesfully!");
			return listBibliotecarios(model);
		}
	}

	@GetMapping("/{id}/delete")
	public String deleteBibliotecario(@PathVariable("id") int id,ModelMap model) {
		Optional<Bibliotecario> bibliotecario=bibliotecariosService.findById(id);
		if(bibliotecario.isPresent()) {
			bibliotecariosService.delete(bibliotecario.get());
			model.addAttribute("message","The bibliotecario was deleted successfully!");
			return listBibliotecarios(model);
		}else {
			model.addAttribute("message","We cannot find the bibliotecario you tried to delete!");
			return listBibliotecarios(model);
		}
	}*/
	
}