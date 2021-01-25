package org.springframework.samples.petclinic.web;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/proveedores")
public class ProveedorController {
	@Autowired
	ProveedorService proveedorService;
	
	
	
	@GetMapping
	public String listProveedores(ModelMap model) {
		String vista = "proveedores/listProveedor";
		Collection<Proveedor> proveedor = proveedorService.findAll();
		model.addAttribute("proveedor", proveedor);
		return vista;
	}
	
	@PostMapping(path="/save")
	public String guardarProveedor(@Valid Proveedor proveedor, BindingResult result, ModelMap modelmap) {
		String vista = "proveedores/listProveedor";
		if(result.hasErrors()) {
			modelmap.addAttribute("proveedor", proveedor);
			return "proveedores/editProveedor";
		}else {
			proveedorService.save(proveedor);
			modelmap.addAttribute("message", "Proveedor guardado correctamente");
			vista = listProveedores(modelmap);
		}
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearProveedor(ModelMap modelmap) {
		String vista = "proveedores/editProveedor";
		modelmap.addAttribute("proveedor", new Proveedor());
		return vista;
	}
	
	
	
	

}
