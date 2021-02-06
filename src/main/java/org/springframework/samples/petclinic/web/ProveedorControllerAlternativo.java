package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/proveedores")
public class ProveedorControllerAlternativo {

	@Autowired
	private ProveedorService proveedorService;
	
	@RequestMapping(value="/getAll", method = RequestMethod.GET)
	public @ResponseBody Iterable<Proveedor> getProveedores(){
		return proveedorService.findAll();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public @ResponseBody Proveedor getProveedor(@PathVariable int id){
		return proveedorService.findById(id).get();
	}
	
	@GetMapping(path="/verTodas")
	public String listProveedores() {
		return "proveedores/proveedoresAPI";
	}
	
	@GetMapping(path="/ver/{id}")
	public String listProveedor(@PathVariable int id) {
		return "proveedores/proveedoresAPI";
	}
}
