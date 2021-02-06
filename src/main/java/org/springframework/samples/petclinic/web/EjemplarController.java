package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Disponibilidad;
import org.springframework.samples.petclinic.model.Ejemplar;
import org.springframework.samples.petclinic.service.EjemplarService;
import org.springframework.samples.petclinic.service.LibroService;
import org.springframework.samples.petclinic.service.PrestamoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ejemplares")
public class EjemplarController {

	
	@Autowired
	EjemplarService ejemplaresService;
	
	@Autowired
	LibroService libroService;
	
	@Autowired
	PrestamoService prestamosService;
	
	@ModelAttribute("listaLibros")
	public Map<Integer, String> titulosLibros() {
		return libroService.findAll().stream().collect(Collectors.toMap(x->x.getId(), y->y.getTitulo()));
	}
	
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
			ejemplar.setDisponibilidad(Disponibilidad.DISPONIBLE);
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
	
	@GetMapping(path="/descatalogar/{ejemplarId}")
	public String descatalogarLibro(@PathVariable("ejemplarId") int ejemplarId, ModelMap modelmap) {
		String vista = "ejemplar/listEjemplar";
		Optional<Ejemplar> ejemplar = ejemplaresService.findById(ejemplarId);
		if (ejemplar.isPresent()) {
			if(ejemplar.get().getDisponibilidad()!=Disponibilidad.DISPONIBLE) {
				modelmap.addAttribute("message", "No se puede descatalogar el ejemplar: Se encuentra en un préstamo actualmente.");
			}
			else {
				ejemplar.get().setDisponibilidad(Disponibilidad.DESCATALOGADO);
				ejemplaresService.save(ejemplar.get());
				modelmap.addAttribute("message", "Ejemplar descatalogado con éxito");
			}
		}
		else {
			modelmap.addAttribute("message", "Ejemplar no encontrado");
		}
		vista = listEjemplares(modelmap);
		return vista;
	}
	
    @GetMapping(path="/edit/{ejemplarId}")
    public String modificarEstado(@PathVariable("ejemplarId") int ejemplarId, ModelMap modelmap) {
        String vista = "ejemplares/editEstado";

        Optional<Ejemplar> ejemplar = ejemplaresService.findById(ejemplarId);
        
        if(ejemplar.isPresent()) {
            modelmap.addAttribute("ejemplar", ejemplar.get());
        }
        else {
            modelmap.addAttribute("message", "Ejemplar no encontrado");
            vista = listEjemplares(modelmap);
        }
        
        return vista;
    }
    
    @PostMapping(path="/saveEstado")
    public String guardarEstadoEjemplar(@Valid Ejemplar ejemplar, BindingResult result, ModelMap modelmap) {
        String vista = "ejemplares/listEjemplar";
        if(result.hasErrors()) {
            modelmap.addAttribute("ejemplar", ejemplar);
            return "ejemplares/editEstado";
        }else {
            ejemplaresService.save(ejemplar);
            modelmap.addAttribute("message", "Estado del ejemplar modificado correctamente");
            vista = listEjemplares(modelmap);
        }
        return vista;
    }
}
