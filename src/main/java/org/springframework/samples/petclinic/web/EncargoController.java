package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Encargo;
import org.springframework.samples.petclinic.service.EncargoService;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.samples.petclinic.service.exceptions.LimiteEjemplaresException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/encargos")
public class EncargoController {

	@Autowired
	EncargoService encargosService;
	
	@Autowired
    ProveedorService proveedorService;

    @ModelAttribute("listaProveedores")
    public Map<Integer, String> listaAutores() {
        return proveedorService.findAll().stream().collect(Collectors.toMap(x->x.getId(), y->y.getNombre()));
    }

	@GetMapping
	public String listEncargos(ModelMap model) {
		String vista = "encargos/listEncargo";
		Collection<Encargo> encargos = encargosService.findAll();
		model.addAttribute("encargos", encargos);
		return vista;
	}

	@PostMapping(path = "/save")
	public String guardarEncargo(@Valid Encargo encargo, BindingResult result, ModelMap modelmap)
			throws LimiteEjemplaresException {
		String vista = "encargos/listEncargo";
		if (result.hasErrors()) {
			modelmap.addAttribute("message", result.getAllErrors().toString());
			modelmap.addAttribute("encargo", encargo);
			return "encargos/editEncargo";
		} else {
			try {
				encargosService.save(encargo);
				modelmap.addAttribute("message", "Encargo guardado correctamente");
			} catch (LimiteEjemplaresException e) {
				modelmap.addAttribute("message", "Demasiados ejemplares del libro");
			}
			vista = listEncargos(modelmap);
		}
		return vista;
	}

	@GetMapping(path = "/new")
	public String crearEncargo(ModelMap modelmap) {
		String vista = "encargos/editEncargo";
		modelmap.addAttribute("encargo", new Encargo());
		return vista;
	}
}