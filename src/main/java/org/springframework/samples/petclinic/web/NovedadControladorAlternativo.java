package org.springframework.samples.petclinic.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Novedad;
import org.springframework.samples.petclinic.service.NovedadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Controller
@RequestMapping("/api/novedades")
public class NovedadControladorAlternativo {

	@Autowired
	private NovedadService novedadService;
	
	@RequestMapping(value="/get", method = RequestMethod.GET)
	public @ResponseBody Iterable<Novedad> getNovedades(){
		return novedadService.findAll();
	}
	
	@RequestMapping(value="/get/Today", method = RequestMethod.GET)
	public @ResponseBody Collection<Novedad> getNovedadesHoy(){
		return novedadService.findNovedadesHoy();
	}
	
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET)
	public @ResponseBody Novedad getNovedad(@PathVariable int id){
		return novedadService.findById(id).get();
	}
	
	@PostMapping("/postNovedad")
	@ResponseBody
	public void postNovedad(@RequestBody Novedad newNovedad) {
	    novedadService.save(newNovedad);
	  }
	
	@GetMapping(path="")
	public String listNovedades() {
		return "novedades/novedadesAPI";
	}
	
	@GetMapping(path="/{id}")
	public String listNovedad(@PathVariable int id) {
		return "novedades/novedadesAPI";
	}
	
	@GetMapping(path="/Today")
	public String listNovedadesHoy() {
		return "novedades/novedadesAPI";
	}
}
