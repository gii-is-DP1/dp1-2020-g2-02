package org.springframework.samples.petclinic.web;


 import java.security.Principal;

import javax.validation.Valid;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.samples.petclinic.model.Miembro;
 import org.springframework.samples.petclinic.model.Puntuacion;
 import org.springframework.samples.petclinic.model.User;
 import org.springframework.samples.petclinic.service.LibroService;
 import org.springframework.samples.petclinic.service.MiembroService;
 import org.springframework.samples.petclinic.service.PuntuacionService;
 import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.exceptions.LibroNoPrestadoAnteriormenteException;
import org.springframework.stereotype.Controller;
 import org.springframework.ui.ModelMap;
 import org.springframework.validation.BindingResult;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestMapping;

 @Controller
 @RequestMapping("/puntuacion")
 public class PuntuacionController {

 	@Autowired
 	PuntuacionService puntuacionService;

 	@Autowired
 	UserService userService;

 	@Autowired
 	MiembroService miembroService;

 	@Autowired
 	LibroService librosService;


 	@GetMapping(path="/valorar/{libroId}")
 	public String valorar(@PathVariable("libroId") int libroId, ModelMap model) {

 		String vista = "libros/valorarLibro";
 		model.addAttribute("libro", librosService.findById(libroId).get());
 		model.addAttribute("puntuacion", new Puntuacion());
 		return vista;
 	} 

 	@PostMapping(path="/save")
 	public String guardarPuntuacion(@Valid Puntuacion puntuacion, BindingResult result, ModelMap modelmap, Principal principal) {
 		String vista = "welcome";
 		if(result.hasErrors()) {
 			modelmap.addAttribute("puntuacion", puntuacion);
 			modelmap.addAttribute("message", "Hay fallos en el formulario");
 			return "libros/valorarLibro";
 		}
 		else {
 			User user = userService.findByUsername(principal.getName());
 	 		Miembro miembro = miembroService.findByUser(user);
	 		puntuacion.setMiembro(miembro);
	 		
 			try {
 	 			puntuacionService.savePuntuacion(puntuacion);
 	 			modelmap.addAttribute("message", "Libro valorado correctamente");
 			}
 			catch (LibroNoPrestadoAnteriormenteException e) {
 				modelmap.addAttribute("message", "Este libro no ha sido prestado por usted anteriormente");
 			}
 		}
 		return vista; 
 	}
 }