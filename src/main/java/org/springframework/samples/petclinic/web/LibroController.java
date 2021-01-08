package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Disponibilidad;
import org.springframework.samples.petclinic.model.Ejemplar;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.model.Miembro;
import org.springframework.samples.petclinic.model.Prestamo;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.EjemplarService;
import org.springframework.samples.petclinic.service.LibroService;
import org.springframework.samples.petclinic.service.MiembroService;
import org.springframework.samples.petclinic.service.PrestamoService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping
	public String listLibros(ModelMap model, @RequestParam(required = false) String q, @RequestParam(required = false) String qAutor, @RequestParam(required = false) String qEditorial) {
		String vista = "libros/listLibro";
		Collection<Libro> libros = librosService.findAll();
		//Halla si hay algún ejemplar disponible para cada libro.
		Map<Integer, Boolean> disponibilidad = libros.stream().collect(Collectors.toMap(x->x.getId(), y->!ejemplarService.findDisponibles(y).isEmpty()));
		//Filtra por nombre en caso de que haya una búsqueda.
		if(q != null && !q.isEmpty()) libros=libros.stream().filter(x->x.getTitulo().toLowerCase().contains(q.toLowerCase())).collect(Collectors.toList());
		if(qAutor != null && !qAutor.isEmpty()) libros=libros.stream().filter(x->x.getAutores().stream().anyMatch(y->y.getNombre().toLowerCase().contains(qAutor.toLowerCase()))).collect(Collectors.toList());
		if(qEditorial != null && !qEditorial.isEmpty()) libros=libros.stream().filter(x->x.getEditorial().getNombre().toLowerCase().contains(q.toLowerCase())).collect(Collectors.toList());
		model.addAttribute("libros",libros);
		model.addAttribute("disponibilidad", disponibilidad);
		
		return vista;
	}
	
	@GetMapping(path="/reservar/{libroId}")
	public String reservar(@PathVariable("libroId") int libroId, ModelMap model, Principal principal) {
		String vista = listLibros(model,null,null,null);
		
		//Comprueba si el libro existe
		Optional<Libro> libro = librosService.findById(libroId);
		if(!libro.isPresent()) {
			model.addAttribute("message","Libro no existente");
			return vista;
		}
		
		//Comprueba si el usuario tiene ya en préstamo ese libro.
		User user = userService.findByUsername(principal.getName());
		Miembro miembro = miembroService.findByUser(user);
		Optional<Prestamo> prestamoExistente = prestamoService.prestamosDeLibroEnProceso(miembro, libro.get());
		if(prestamoExistente.isPresent()) {
			model.addAttribute("message","Ya tienes ese libro en préstamo");
			return vista;
		}
		
		//Comprueba si el libro no tiene ejemplares disponibles
		Collection<Ejemplar> ejemplaresDisponibles = ejemplarService.findDisponibles(libro.get());
		if(ejemplaresDisponibles.isEmpty()) {
			model.addAttribute("message","Libro no disponible");
		}
		else {
			Ejemplar ejemplar = ejemplaresDisponibles.iterator().next();
			ejemplar.setDisponibilidad(Disponibilidad.RESERVADO);
			Prestamo prestamo = new Prestamo();
			prestamo.setFechaPrestamo(LocalDate.now());
			prestamo.setFechaDevolucion(LocalDate.now().plusDays(16));
			prestamo.setFinalizado(false);
			prestamo.setEjemplar(ejemplar);
			prestamo.setMiembro(miembro);

			prestamoService.save(prestamo);
			model.addAttribute("message","Libro reservado, acuda a la biblioteca a recogerlo.");
		}
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
