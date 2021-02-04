package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Miembro;
import org.springframework.samples.petclinic.model.Sugerencia;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.MiembroService;
import org.springframework.samples.petclinic.service.SugerenciaService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sugerencias")
public class SugerenciaController {

	@Autowired
	SugerenciaService sugerenciaService;
	
	@Autowired
	MiembroService miembroService;
	
	@Autowired
	UserService userService;

	@GetMapping
	public String listSugerencias(ModelMap model) {
		String vista = "sugerencias/listSugerencia";
		Collection<Sugerencia> sugerencias = sugerenciaService.findAllOrderByTituloLibro();
		model.addAttribute("sugerencias", sugerencias);
		return vista;
	}

	@GetMapping(path = "/new")
	public String crearEncargo(ModelMap modelmap) {
		String vista = "sugerencias/editSugerencia";
		modelmap.addAttribute("sugerencia", new Sugerencia());
		return vista;
	}

	@PostMapping(path = "/save")
	public String guardarSugerencia(@Valid Sugerencia sugerencia, BindingResult result, ModelMap modelmap, Principal principal) {
		String vista = "sugerencias/listSugerencia";
		
		User user = userService.findByUsername(principal.getName());
		Miembro miembro = miembroService.findByUser(user);
		sugerencia.setMiembro(miembro);
		
		if (result.hasErrors()) {
			modelmap.addAttribute("sugerencia", sugerencia);
			return "sugerencias/editSugerencia";
		} else {
			sugerenciaService.save(sugerencia);
			modelmap.addAttribute("message", "Sugerencia guardada correctamente");
			vista = listSugerencias(modelmap);
		}
		return vista;
	}
}
