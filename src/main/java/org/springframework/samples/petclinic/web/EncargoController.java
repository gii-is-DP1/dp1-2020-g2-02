package org.springframework.samples.petclinic.web;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Encargo;
import org.springframework.samples.petclinic.service.EncargoService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/encargos")
public class EncargoController {

	@Autowired
	EncargoService encargosService;

	@Autowired
	UserService userService;

	@GetMapping
	public String listEncargos(ModelMap model) {
		String vista = "encargos/listEncargo";
		Collection<Encargo> encargos = encargosService.findAll();
		model.addAttribute("encargos", encargos);
		return vista;
	}

	@PostMapping(path = "/save")
	public String guardarEncargo(@Valid Encargo encargo, BindingResult result, ModelMap modelmap) {
		String vista = "encargos/listEncargo";
		if (result.hasErrors()) {
			modelmap.addAttribute("miembro", encargo);
			return "encargos/editEncargo";
		} else {
			encargosService.save(encargo);
			modelmap.addAttribute("message", "Encargo guardado correctamente");
			vista = listEncargos(modelmap);
		}
		return vista;
	}

	@GetMapping(path = "/new")
	public String crearEncargo(ModelMap modelmap) {
		String vista = "encargos/editEncargo";
		modelmap.addAttribute("encargo", new Encargo());
		return vista;
	}
}