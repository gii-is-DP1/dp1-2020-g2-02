package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.MiembroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.samples.petclinic.model.Miembro;

@Controller
@RequestMapping("/miembros")
public class MiembroController {
	
	@Autowired
	MiembroService miembrosService;
	
	@GetMapping
	public String listMiembros(ModelMap model) {
		String vista = "miembros/listMiembros";
		Collection<Miembro> miembros = miembrosService.findAll();
		model.addAttribute("miembros", miembros);
		return vista;
	}
	
	@PostMapping(path="/save")
	public String guardarMiembro(@Valid Miembro miembro, BindingResult result, ModelMap modelmap) {
		String vista = "miembros/listMiembro";
		if(result.hasErrors()) {
			modelmap.addAttribute("miembro", miembro);
			return "miembros/editMiembro";
		}else {
			miembrosService.save(miembro);
			modelmap.addAttribute("message", "Miembro guardado correctamente");
			vista = listMiembros(modelmap);
		}
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearMiembro(ModelMap modelmap) {
		String vista = "miembros/editMiembro";
		modelmap.addAttribute("miembro", new Miembro());
		return vista;
	}
	
	@GetMapping(path="/delete/{miembroId}")
	public String borrarMiembro(@PathVariable("miembroId") int miembroId, ModelMap modelmap) {
		String vista = "miembros/listMiembros";
		Optional<Miembro> miembro = miembrosService.findById(miembroId);
		if(miembro.isPresent()) {
			miembrosService.delete(miembro.get());
			modelmap.addAttribute("message", "Miembro eliminado correctamente");
			vista = listMiembros(modelmap);
		}else {
			modelmap.addAttribute("message", "Miembro no encontrado");
		}
		vista = listMiembros(modelmap);
		return vista;
	}	
}