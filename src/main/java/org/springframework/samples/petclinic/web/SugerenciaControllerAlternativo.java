package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Sugerencia;
import org.springframework.samples.petclinic.service.SugerenciaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Controller
@RequestMapping("/api/sugerencias")
public class SugerenciaControllerAlternativo {
	
	@Autowired
	private SugerenciaService sugerenciaService;
	
	@RequestMapping(value="/getAll", method = RequestMethod.GET)
	public @ResponseBody Iterable<Sugerencia> getSugerencias(){
		return sugerenciaService.findAllOrderByTituloLibro();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public @ResponseBody Sugerencia getSugerencia(@PathVariable int id){
		return sugerenciaService.findById(id).get();
	}
	
	@PostMapping("/postSugerencia")
	@ResponseBody
	public void postSugerencia(@RequestBody Sugerencia newSugerencia) {
		sugerenciaService.save(newSugerencia);
	 }
	
	@GetMapping(path="/verTodas")
	public String listSugerencias() {
		return "sugerencias/sugerenciasAPI";
	}
	
	@GetMapping(path="/ver/{id}")
	public String listSugerencia(@PathVariable int id) {
		return "sugerencias/sugerenciasAPI";
	}

}