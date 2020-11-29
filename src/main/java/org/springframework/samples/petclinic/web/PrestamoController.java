
package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Prestamo;
import org.springframework.samples.petclinic.service.PrestamoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

	@Controller
	@RequestMapping("/prestamos")
	public class PrestamoController {
		
		@Autowired
		PrestamoService prestamosService;
		
		@GetMapping
		public String listPrestamos(ModelMap model) {
			String vista = "miembros/listMiembros";
			Collection<Prestamo> prestamos = prestamosService.findAll();
			model.addAttribute("prestamos", prestamos);
			return vista;
		}
		
		@PostMapping(path="/save")
		public String guardarMiembro(@Valid Prestamo prestamos, BindingResult result, ModelMap modelmap) {
			String vista = "prestamos/listPrestamo";
			if(result.hasErrors()) {
				modelmap.addAttribute("prestamo", prestamos);
				return "prestamos/editPrestamo";
			}else {
				prestamosService.save(prestamos);
				modelmap.addAttribute("message", "Prestamo guardado correctamente");
				vista = listPrestamos(modelmap);
			}
			return vista;
		}
		
		@GetMapping(path="/new")
		public String crearPrestamo(ModelMap modelmap) {
			String vista = "prestamos/editPrestamo";
			modelmap.addAttribute("prestamo", new Prestamo());
			return vista;
		}
		
		
	}
	
