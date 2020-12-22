package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Disponibilidad;
import org.springframework.samples.petclinic.model.Ejemplar;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.service.EjemplarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ejemplares")
public class EjemplarController {

	
	@Autowired
	EjemplarService ejemplaresService;
	
	@GetMapping
	public String listEjemplares(ModelMap model) {
		String vista = "ejemplares/listEjemplar";
		Collection<Ejemplar> Ejemplares = ejemplaresService.findAll();
		model.addAttribute("ejemplares", Ejemplares);
		return vista;
	}
	
	@PostMapping(path="/save")
	public String guardarEjemplar(@Valid Ejemplar ejemplar, BindingResult result, ModelMap modelmap) {
		String vista = "ejemplares/listEjemplar";
		if(result.hasErrors()) {
			modelmap.addAttribute("ejemplar", ejemplar);
			return "ejemplares/editEjemplar";
		}else {
			ejemplaresService.save(ejemplar);
			modelmap.addAttribute("message", "Ejemplar guardado correctamente");
			vista = listEjemplares(modelmap);
		}
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearEjemplar(ModelMap modelmap) {
		String vista = "ejemplares/editEjemplar";
		modelmap.addAttribute("ejemplar", new Ejemplar());
		return vista;
	}
	
	/*@GetMapping(path="/delete/{ejemplarId}")
	public String borrarEjemplar(@PathVariable("ejemplarId") int ejemplarId, ModelMap modelmap) {
		String vista = "libros/listLibro"; 
		Optional<Ejemplar> ejemplar = ejemplaresService.findById(ejemplarId);
		if(ejemplar.isPresent()) {
			ejemplaresService.delete(ejemplar.get());
			modelmap.addAttribute("message", "Ejemplar eliminado correctamente");
		}else {
			modelmap.addAttribute("message", "Ejemplar no encontrado");
		}
		vista = listEjemplares(modelmap);
		return vista;
	}*/
	@GetMapping(path="/descatalogar/{ejemplarId}")
	public String descatalogarLibro(@PathVariable("ejemplarId") int ejemplarId, ModelMap modelmap) {
		String vista = "ejemplar/listEjemplar";
		Optional<Ejemplar> ejemplar = ejemplaresService.findById(ejemplarId);
		if (ejemplar.isPresent()) {
			ejemplar.get().setDisponibilidad(Disponibilidad.DESCATALOGADO);
			ejemplaresService.save(ejemplar.get());
		}
		else {

			modelmap.addAttribute("message", "Ejemplar no encontrado");
		}
		vista = listEjemplares(modelmap);
		return vista;
	}
}
