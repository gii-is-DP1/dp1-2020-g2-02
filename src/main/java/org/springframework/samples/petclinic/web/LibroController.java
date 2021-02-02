package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.model.Miembro;
import org.springframework.samples.petclinic.model.Puntuacion;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AutorService;
import org.springframework.samples.petclinic.service.EditorialService;
import org.springframework.samples.petclinic.service.EjemplarService;
import org.springframework.samples.petclinic.service.GeneroService;
import org.springframework.samples.petclinic.service.LibroService;
import org.springframework.samples.petclinic.service.MiembroService;
import org.springframework.samples.petclinic.service.PrestamoService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.exceptions.LibroNoDisponibleException;
import org.springframework.samples.petclinic.service.exceptions.LibroNoExistenteException;
import org.springframework.samples.petclinic.service.exceptions.LibroYaEnPrestamoException;
import org.springframework.samples.petclinic.service.exceptions.LimitePrestamosException;
import org.springframework.samples.petclinic.service.exceptions.PrestamoConRetrasoException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libros")
public class LibroController {

	
	@Autowired
	LibroService librosService;
	
	@Autowired
	EjemplarService ejemplarService;
	
	@Autowired
	PrestamoService prestamoService;

	@Autowired
	UserService userService;
	
	@Autowired
	MiembroService miembroService;
	
	@Autowired
	AutorService autorService;
	
	@Autowired
	EditorialService editorialService;
	
	@Autowired
	GeneroService generoService;
	
	@ModelAttribute("generos")
    public Map<Integer, String> listaGeneros() {
        return generoService.findAll().stream().collect(Collectors.toMap(x->x.getId(), y->y.getGenero()));
    }
	
	@ModelAttribute("autores")
	public Map<Integer, String> listaAutores() {
		return autorService.findAll().stream().collect(Collectors.toMap(x->x.getId(), y->y.getNombre() + " " + y.getApellidos()));
	}
	
	@ModelAttribute("editoriales")
	public Map<Integer, String> listaEditoriales() {
		return editorialService.findAll().stream().collect(Collectors.toMap(x->x.getId(), y->y.getNombre()));
	}

	@GetMapping
	public String listLibros(ModelMap model, @RequestParam(required = false) String q, @RequestParam(required = false) String qAutor, @RequestParam(required = false) String qEditorial) {
		String vista = "libros/listLibro";
		Collection<Libro> libros = librosService.findAll();
		
		//Halla si hay algún ejemplar disponible para cada libro.
		Map<Integer, Boolean> disponibilidad = libros.stream().collect(Collectors.toMap(x->x.getId(), y->!ejemplarService.findDisponibles(y).isEmpty()));
		
		
		//Halla las puntuaciones de los libros
 		Map<Libro, Double> puntuaciones = new HashMap<>();
 		Iterator<Libro> it = libros.iterator();
 		while(it.hasNext()) {
 			Libro l = it.next();
 			puntuaciones.put(l, librosService.getNotaMedia(l));
 		}
		
		//Filtra y ordena el resultado.
		if(q != null && !q.isEmpty()) 
			libros=libros.stream().filter(x->x.getTitulo().toLowerCase().contains(q.toLowerCase())).collect(Collectors.toList());
		if(qAutor != null && !qAutor.isEmpty()) 
			libros=libros.stream().filter(x->x.getAutores().stream().anyMatch(y->y.getNombre().toLowerCase().contains(qAutor.toLowerCase()))).collect(Collectors.toList());
		if(qEditorial != null && !qEditorial.isEmpty()) 
			libros=libros.stream().filter(x->x.getEditorial().getNombre().toLowerCase().contains(q.toLowerCase())).collect(Collectors.toList());
		
		model.addAttribute("libros",libros);
		model.addAttribute("puntuaciones",puntuaciones);
		model.addAttribute("disponibilidad", disponibilidad);
		
		return vista;
	}
	
	@GetMapping(path="/reservar/{libroId}")
	public String reservar(@PathVariable("libroId") int libroId, ModelMap model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		Miembro miembro = miembroService.findByUser(user);
		
		try {
			prestamoService.realizarReserva(libroId, miembro);
			model.addAttribute("message","Libro reservado, acuda a la biblioteca a recogerlo.");
		}
		catch(LibroNoExistenteException e) {
			model.addAttribute("message","Libro no existente");
		}
		catch(LimitePrestamosException e) {
			model.addAttribute("message","Ha superado el limite de préstamos, por favor entregue uno de sus préstamos antes de realizar uno nuevo");
		}
		catch(LibroYaEnPrestamoException e) {
			model.addAttribute("message","Ya tienes ese libro en préstamo");
		}
		catch(LibroNoDisponibleException e) {
			model.addAttribute("message","Libro no disponible");
		}
		catch(PrestamoConRetrasoException e) {
			model.addAttribute("message","Por favor, entregue su préstamo pendiente de devolución antes de realizar uno nuevo");
		}
		
		String vista = listLibros(model,null,null,null);
		return vista;
	}
	
	
	@PostMapping(path="/save")
	public String guardarLibro(@Valid Libro libro, BindingResult result, ModelMap modelmap) {
		String vista = "libros/listLibro";
		if(result.hasErrors()) {
			modelmap.addAttribute("libro", libro);
			return "libros/editLibro";
		}else {
			librosService.save(libro);
			modelmap.addAttribute("message", "Libro guardado correctamente");
			vista = listLibros(modelmap,null,null,null);
		}
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearLibro(ModelMap modelmap) {
		String vista = "libros/editLibro";
		modelmap.addAttribute("libro", new Libro());
		return vista;
	}	
	
}
