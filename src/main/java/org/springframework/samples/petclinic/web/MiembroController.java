package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Miembro;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.MiembroService;
import org.springframework.samples.petclinic.service.UserService;
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
@RequestMapping("/miembros")
public class MiembroController {

	@Autowired
	MiembroService miembrosService;

	@Autowired
	UserService userService;

	@GetMapping
	public String listMiembros(ModelMap model) {
		String vista = "miembros/listMiembro";
		Collection<Miembro> miembros = miembrosService.findAll();
		model.addAttribute("miembros", miembros);
		return vista;
	}

	@PostMapping(path = "/save")
	public String guardarMiembro(@Valid Miembro miembro, BindingResult result, ModelMap modelmap) {
		String vista = "miembros/listMiembro";
		if (result.hasErrors()) {
			modelmap.addAttribute("miembro", miembro);
			log.warn("Datos del miembro incorrectos " + result.getAllErrors());
			return "miembros/editMiembro";
		} else if (userService.findUser(miembro.getUser().getUsername()).isPresent()) {
			modelmap.addAttribute("message", "Usuario ya existente");
			modelmap.addAttribute("miembro", miembro);
			log.warn("Miembro con nombre de usuario " + miembro.getUser().getUsername() + " ya existente");
			return "miembros/editMiembro";
		} else {
			miembrosService.save(miembro);
			modelmap.addAttribute("message", "Miembro guardado correctamente");
			vista = listMiembros(modelmap);
			log.info("Miembro con id: " + miembro.getId() + " guardado correctamente");
		}
		return vista;
	}

	@GetMapping(path = "/new")
	public String crearMiembro(ModelMap modelmap) {
		String vista = "miembros/editMiembro";
		modelmap.addAttribute("miembro", new Miembro());
		return vista;
	}

	@GetMapping(path = "/habilitar/{miembroId}")
	public String habilitarMiembro(@PathVariable("miembroId") int miembroId, ModelMap modelmap) {
		String vista = "miembros/listMiembros";
		Optional<Miembro> miembro = miembrosService.findById(miembroId);
		if (miembro.isPresent()) {
			User user = miembro.get().getUser();
			user.setEnabled(true);
			userService.save(user);
			modelmap.addAttribute("message", "Miembro habilitado correctamente");
			vista = listMiembros(modelmap);
			log.info("Miembro con id: " + miembroId + " habilitado correctamente");
		} else {
			modelmap.addAttribute("message", "Miembro no encontrado");
			log.warn("Miembro con id: " + miembroId + " no encontrado");
		}
		vista = listMiembros(modelmap);
		return vista;
	}

	@GetMapping(path = "/deshabilitar/{miembroId}")
	public String deshabilitarMiembro(@PathVariable("miembroId") int miembroId, ModelMap modelmap) {
		String vista = "miembros/listMiembros";
		Optional<Miembro> miembro = miembrosService.findById(miembroId);
		if (miembro.isPresent()) {
			User user = miembro.get().getUser();
			user.setEnabled(false);
			userService.save(user);
			modelmap.addAttribute("message", "Miembro deshabilitado correctamente");
			vista = listMiembros(modelmap);
			log.info("Miembro con id: " + miembroId + " deshabilitado correctamente");
		} else {
			modelmap.addAttribute("message", "Miembro no encontrado");
			log.warn("Miembro con id: " + miembroId + " no encontrado");
		}
		vista = listMiembros(modelmap);
		return vista;
	}
}