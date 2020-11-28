package org.springframework.samples.petclinic.web;


import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Bibliotecario;
import org.springframework.samples.petclinic.model.Novedad;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.BibliotecarioService;
import org.springframework.samples.petclinic.service.NovedadService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/novedades")
public class NovedadController {
	
	@Autowired
	private NovedadService novedadService;
	
	@Autowired
	private BibliotecarioService bibliotecarioService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String listNovedades(ModelMap model) {
		String vista = "novedades/listNovedad";
		Iterable<Novedad> novedades = novedadService.findAll();
		model.addAttribute("novedades", novedades);
		return vista;
	}
	
	@PostMapping(path="/save")
	public String guardarNovedad(@Valid Novedad novedad, BindingResult result, ModelMap modelmap, Principal principal) {
		String vista = "novedades/listNovedad";
		System.out.println(novedad);
		if(result.hasErrors()) {
			modelmap.addAttribute("message", result.toString());
			modelmap.addAttribute("novedad", novedad);
			modelmap.addAttribute("message", "Hay fallos en el formulario.");
			return "novedades/editNovedad";
		}else {
			User user = userService.findByUsername(principal.getName());
			Bibliotecario biblio = bibliotecarioService.findByUser(user);
			novedad.setBibliotecario(biblio);
			novedadService.save(novedad);
			modelmap.addAttribute("message", "Novedad guardada correctamente.");
			vista = listNovedades(modelmap);
		}
		return vista;
	}
	
	@GetMapping(path="/new")
	public String publicarNovedad(ModelMap modelmap) {
		String vista = "novedades/editNovedad";
		modelmap.addAttribute("novedad", new Novedad());
		return vista;
	}
}
