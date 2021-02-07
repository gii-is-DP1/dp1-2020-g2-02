package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Autor;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.model.Miembro;
import org.springframework.samples.petclinic.model.Sugerencia;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AutorService;
import org.springframework.samples.petclinic.service.LibroService;
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
	LibroService libroService;

	@Autowired
	AutorService autorService;
	
	@Autowired
	UserService userService;

	@GetMapping
	public String listSugerencias(ModelMap model) {
		String vista = "sugerencias/listSugerencia";
		List<Sugerencia> sugerencias = sugerenciaService.findAllOrderByTituloLibro();
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
			boolean b = false;
			Iterator<Libro> it = libroService.findAll().iterator();
			while(it.hasNext()) {
				Libro l = it.next();
				if (l.getTitulo().equals(sugerencia.getTituloLibro())) {
					if(l.getAutores().stream().anyMatch(x->autorService.getNombreCompleto(x).equals(sugerencia.getNombreAutor()))){
						b = true;
					}
				}
			}
			if (b) {
				modelmap.addAttribute("message", "Libro ya en catálogo");
				vista = listSugerencias(modelmap);
			} else {
				sugerenciaService.save(sugerencia);
				modelmap.addAttribute("message", "Sugerencia guardada correctamente");
				vista = listSugerencias(modelmap);
			}
		}
		
		
		return vista;
	}
}
