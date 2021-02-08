package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cantidad;
import org.springframework.samples.petclinic.model.Encargo;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.service.CantidadService;
import org.springframework.samples.petclinic.service.EncargoService;
import org.springframework.samples.petclinic.service.LibroService;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.samples.petclinic.service.exceptions.LimiteEjemplaresException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/encargos") 
@SessionAttributes("lineasPedido")
public class EncargoController {

	@Autowired
	EncargoService encargosService;
	
	@Autowired
    ProveedorService proveedorService;
	
	@Autowired
    LibroService libroService;
	
	@Autowired
    CantidadService cantidadService;

    @ModelAttribute("listaProveedores")
    public Map<Integer, String> listaProveedores() {
        return proveedorService.findAll().stream().collect(Collectors.toMap(x->x.getId(), y->y.getNombre()));
    }
    
    @ModelAttribute("listaLibros")
    public Collection<Libro> listaLibros() {
        return libroService.findAll();
    }
    
    @ModelAttribute("lineasPedido")
    public List<Cantidad> lineasPedido() {
        return new ArrayList<Cantidad>();
    }

	@GetMapping
	public String listEncargos(ModelMap model) {
		String vista = "encargos/listEncargo";
		Collection<Encargo> encargos = encargosService.findAll();
		model.addAttribute("encargos", encargos);
		return vista;
	}

	@PostMapping(path = "/save")
	public String guardarEncargo(@Valid Encargo encargo, BindingResult result, ModelMap modelmap, HttpServletRequest request, @ModelAttribute("lineasPedido") ArrayList<Cantidad> lineasPedido)
			throws LimiteEjemplaresException {
		
		String vista = "encargos/editEncargo";

		//Guarda el encargo.
		if(request.getParameter("guardar")!=null) {
			if (result.hasErrors()) {
				modelmap.addAttribute("encargo", encargo);
				log.warn("Datos del encargo incorrectos " + result.getAllErrors());
			} else {
				try {
					encargo.setCantidad(lineasPedido);
					encargosService.save(encargo);
					for(Cantidad c: lineasPedido) {
						c.setEncargo(encargo);
						cantidadService.save(c);
					}
					modelmap.addAttribute("message", "Encargo guardado correctamente");
					vista = listEncargos(modelmap);
					log.info("Encargo con id: " + encargo.getId() + " guardado correctamente");
				} catch (LimiteEjemplaresException e) {
					modelmap.addAttribute("message", "Demasiados ejemplares del libro");
					log.warn("Demasiados ejemplares del libro");
				}
			}
		} 
		//Añade el libro y su cantidad al pedido
		else if(request.getParameter("addLibro")!=null){
			String idLibro = request.getParameter("libroEncargo");
			String numEjemplares = request.getParameter("numEjemplares");
			String precioUnitario = request.getParameter("precioUnitario");
			try{
				Cantidad cantidad = new Cantidad();
				cantidad.setPrecioUnitario(Double.valueOf(precioUnitario));
				Libro libro = libroService.findById(Integer.valueOf(idLibro)).get();
				cantidad.setLibro(libro);
				cantidad.setUnidades(Integer.valueOf(numEjemplares));
				
				lineasPedido.add(cantidad);
				
				modelmap.addAttribute("lineasPedido", lineasPedido);
				modelmap.addAttribute("message", "Añadido libro al pedido.");
				log.info("Libro " + idLibro + " y cantidad " + numEjemplares + " añadidos al pedido");
			}
			catch(Exception e) {
				modelmap.addAttribute("message", "Error al añadir libros al pedido.");
				log.warn("Error al añadir libros al pedido.");
			}
		}
			
		return vista;
	}
	@GetMapping(path = "/new")
	public String crearEncargo(ModelMap modelmap) {
		String vista = "encargos/editEncargo";
		Encargo encargo = new Encargo();
		modelmap.addAttribute("encargo", encargo);
		modelmap.addAttribute("lineasPedido", new ArrayList<Cantidad>());
		return vista;
	}
}