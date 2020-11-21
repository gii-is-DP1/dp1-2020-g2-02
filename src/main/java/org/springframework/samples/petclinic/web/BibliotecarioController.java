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
			vista = listBibliotecarios(modelmap);
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
			vista = listBibliotecarios(modelmap);
		}else {
			modelmap.addAttribute("message", "Bibliotecario no encontrado");
		}
		vista = listBibliotecarios(modelmap);
		return vista;
	}	
}