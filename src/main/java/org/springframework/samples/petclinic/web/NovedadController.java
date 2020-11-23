package org.springframework.samples.petclinic.web;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Bibliotecario;
import org.springframework.samples.petclinic.model.Novedad;
import org.springframework.samples.petclinic.service.NovedadService;
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
	
	@GetMapping
	public String listNovedades(ModelMap model) {
		String vista = "novedades/listNovedad";
		Iterable<Novedad> novedades = novedadService.findAll();
		model.addAttribute("novedades", novedades);
		return vista;
	}
	
	@PostMapping(path="/save")
	public String guardarNovedad(@Valid Novedad novedad, BindingResult result, ModelMap modelmap) {
		String vista = "novedades/listNovedad";
		if(result.hasErrors()) {
			
			modelmap.addAttribute("message", result.toString());
			modelmap.addAttribute("novedad", novedad);
			return "novedades/editNovedad";
		}else {
			novedadService.save(novedad);
			modelmap.addAttribute("message", "Novedad guardada correctamente");
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
