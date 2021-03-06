package org.springframework.samples.petclinic.web;


 import java.security.Principal;
import java.util.Iterator;

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

import lombok.extern.slf4j.Slf4j;

 @Slf4j
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
 	
 	@Autowired
 	PrestamoController prestamoController;


 	@GetMapping(path="/valorar/{libroId}")
 	public String valorar(@PathVariable("libroId") int libroId, ModelMap model) {

 		String vista = "libros/valorarLibro";
 		model.addAttribute("libro", librosService.findById(libroId).get());
 		model.addAttribute("puntuacion", new Puntuacion());
 		return vista;
 	} 

 	@PostMapping(path="/save")
 	public String guardarPuntuacion(@Valid Puntuacion puntuacion, BindingResult result, ModelMap modelmap, Principal principal) {
 		
 		if(result.hasErrors()) {
 			modelmap.addAttribute("puntuacion", puntuacion);
 			modelmap.addAttribute("message", "Hay fallos en el formulario");
 			log.warn("Datos de la puntuación incorrectos " + result.getAllErrors());
 			return "libros/valorarLibro";
 		}
 		else {
 			User user = userService.findByUsername(principal.getName());
 	 		Miembro miembro = miembroService.findByUser(user);
	 		puntuacion.setMiembro(miembro);
	 		
	 		Iterator<Puntuacion> it = puntuacionService.findAll().iterator();
	 		boolean b = true;
	 		
 			try {
 				while (it.hasNext()) {
 		 			Puntuacion p = it.next();
 		 			if (p.getMiembro().getId()==miembro.getId() && p.getLibro().getId()==puntuacion.getLibro().getId()) {
 		 				p.setPuntaje(puntuacion.getPuntaje());
 		 				puntuacionService.savePuntuacion(p);
 		 	 			modelmap.addAttribute("message", "Valoración actualizada correctamente");
 		 	 			log.info("Valoración con id: " + puntuacion.getId() + " actualizada correctamente");
 		 	 			b = false;
 		 	 			break;
 		 			}
 		 		}
 				if (b) {
 					puntuacionService.savePuntuacion(puntuacion);
 	 	 			modelmap.addAttribute("message", "Libro valorado correctamente");
 	 	 			log.info("Libro con id: " + puntuacion.getLibro().getId() + " valorado correctamente");
 				}
 			}
 			catch (LibroNoPrestadoAnteriormenteException e) {
 				modelmap.addAttribute("message", "Este libro no ha sido prestado por usted anteriormente");
 				log.warn("Libro con id: " + puntuacion.getLibro().getId() + " no ha sido prestado por usted anteriormente");
 			}
 			
 		}
 		return prestamoController.listPrestamosMiembro(modelmap, principal);
 	}
 }