
package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Bibliotecario;
import org.springframework.samples.petclinic.model.Disponibilidad;
import org.springframework.samples.petclinic.model.Ejemplar;
import org.springframework.samples.petclinic.model.Prestamo;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.BibliotecarioService;
import org.springframework.samples.petclinic.service.PrestamoService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

	@Controller
	@RequestMapping("/prestamos")
	public class PrestamoController {
		
		@Autowired
		PrestamoService prestamoService;
		
		@Autowired
		UserService userService;
		
		@Autowired
		BibliotecarioService bibliotecarioService;
		
		@GetMapping
		public String listPrestamos(ModelMap model, @RequestParam(required = false) Boolean b) {
			String vista = "prestamos/listPrestamo";
			LocalDate fecha = LocalDate.now();
			Collection<Prestamo> prestamos;
			if(b != null && b ) {
				prestamos = prestamoService.prestamosConFechaDevolucionTardia(fecha);
			}
			else {
				prestamos = prestamoService.findAll();
			}
			model.addAttribute("prestamos", prestamos);
			return vista;
		}
		
		@PostMapping(path="/save")
		public String guardarPrestamo(@Valid Prestamo prestamos, BindingResult result, ModelMap modelmap) {
			String vista = "prestamos/listPrestamo";
			if(result.hasErrors()) {
				modelmap.addAttribute("prestamo", prestamos);
				return "prestamos/editPrestamo";
			}else {
				prestamoService.save(prestamos);
				modelmap.addAttribute("message", "Prestamo guardado correctamente");
				vista = listPrestamos(modelmap, false);
			}
			return vista;
		}
		
		@GetMapping(path="/new")
		public String crearPrestamo(ModelMap modelmap) {
			String vista = "prestamos/editPrestamo";
			modelmap.addAttribute("prestamo", new Prestamo());
			return vista;
		}
		
		@GetMapping(path="/conceder/{prestamoId}")
		public String concederPrestamo(@PathVariable("prestamoId") int prestamoId, ModelMap modelmap, Principal principal) {
			String vista = listPrestamos(modelmap, false);
			Optional<Prestamo> p = prestamoService.findById(prestamoId);
			if(!p.isPresent()) {
				modelmap.addAttribute("message", "El préstamo no existe.");
				return vista;
			}
			Prestamo prestamo = p.get();
			Ejemplar ej = prestamo.getEjemplar();
			Disponibilidad disp = ej.getDisponibilidad();
			if(disp.equals(Disponibilidad.RESERVADO) && !prestamo.isFinalizado()) {
				ej.setDisponibilidad(Disponibilidad.EN_PRESTAMO);
				User user = userService.findByUsername(principal.getName());
				Bibliotecario biblio = bibliotecarioService.findByUser(user);
				prestamo.setBibliotecario(biblio);
				prestamoService.save(prestamo);
				modelmap.addAttribute("message", "Préstamo concedido correctamente.");
			} else {
				modelmap.addAttribute("message", "El préstamo no se puede conceder (Ya ha finalizado o el ejemplar no se encuentra reservado).");
			}
			return vista;
		}
		
		@GetMapping(path="/finalizar/{prestamoId}")
		public String finalizarPrestamo(@PathVariable("prestamoId") int prestamoId, ModelMap modelmap) {
			String vista = listPrestamos(modelmap, false);
			Optional<Prestamo> p = prestamoService.findById(prestamoId);
			if(!p.isPresent()) {
				modelmap.addAttribute("message", "El préstamo no existe.");
				return vista;
			}
			Prestamo prestamo = p.get();
			Ejemplar ej = prestamo.getEjemplar();
			Disponibilidad disp = ej.getDisponibilidad();
			if(disp.equals(Disponibilidad.EN_PRESTAMO) && !prestamo.isFinalizado()) {
				ej.setDisponibilidad(Disponibilidad.DISPONIBLE);
				prestamo.setFinalizado(true);
				prestamoService.save(prestamo);
				modelmap.addAttribute("message", "Préstamo finalizado correctamente.");
			} else {
				modelmap.addAttribute("message", "El préstamo no se puede finalizar (Ya ha finalizado o el ejemplar no se encuentra en préstamo).");
			}
			return vista;
		}
		
		@GetMapping(path="/rechazar/{prestamoId}")
		public String rechazarPrestamo(@PathVariable("prestamoId") int prestamoId, ModelMap modelmap) {
			String vista = listPrestamos(modelmap, false);
			Optional<Prestamo> p = prestamoService.findById(prestamoId);
			if(!p.isPresent()) {
				modelmap.addAttribute("message", "El préstamo no existe.");
				return vista;
			}
			Prestamo prestamo = p.get();
			Ejemplar ej = prestamo.getEjemplar();
			Disponibilidad disp = ej.getDisponibilidad();
			if(disp.equals(Disponibilidad.RESERVADO) && !prestamo.isFinalizado()) {
				ej.setDisponibilidad(Disponibilidad.DISPONIBLE);
				prestamo.setFinalizado(true);
				prestamoService.save(prestamo);
				modelmap.addAttribute("message", "Préstamo rechazado correctamente.");
			} else {
				modelmap.addAttribute("message", "El préstamo no se puede rechazar (Ya ha finalizado o el ejemplar no se encuentra reservado).");
			}
			return vista;
		}
		
		
		
	}
	
