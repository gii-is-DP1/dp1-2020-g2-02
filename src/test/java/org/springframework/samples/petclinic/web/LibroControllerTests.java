package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
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
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=LibroController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class LibroControllerTests {

	@Autowired
	LibroController controller;
	
	@MockBean
	LibroService libroService;
	
	@MockBean
	MiembroService miembroService;
	
	@MockBean
	UserService userService;
	
	@MockBean
	EjemplarService ejemplarService;
	
	@MockBean
	PrestamoService prestamoService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		Libro libro = new Libro();
		libro.setId(1);
		Libro libro2 = new Libro();
		libro.setId(2);
		
		Ejemplar ej = new Ejemplar();
		ej.setLibro(libro);
		ej.setId(1);
		ej.setDisponibilidad(Disponibilidad.DISPONIBLE);
		Ejemplar ej2 = new Ejemplar();
		ej.setLibro(libro);
		ej.setId(2);
		ej.setDisponibilidad(Disponibilidad.RESERVADO);
		
		List<Ejemplar> l = new ArrayList<>();
		l.add(ej);
		
		Miembro miembro = new Miembro();
		miembro.setId(1);

		User user = new User();
		user.setUsername("Us3r2");
		miembro.setUser(user);
		
		Prestamo p = new Prestamo();
		p.setEjemplar(ej2);
		p.setId(1);
		p.setMiembro(miembro);
		p.setFinalizado(false);
		
		given(ejemplarService.findDisponibles(libro)).willReturn(l);
		given(libroService.findById(1)).willReturn(Optional.of(libro));
		given(libroService.findById(2)).willReturn(Optional.of(libro2));
		given(userService.findByUsername("Us3r2")).willReturn(user);
		given(miembroService.findByUser(user)).willReturn(miembro);
		given(prestamoService.prestamosDeLibroEnProceso(miembro, libro)).willReturn(Optional.of(p));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testLibrosList() throws Exception {
		mockMvc.perform(get("/libros"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("libros"))
			.andExpect(view().name("libros/listLibro"));
	}
	@WithMockUser(value = "Us3r")
	@Test
	void testReservarLibro() throws Exception {
		mockMvc.perform(get("/libros/reservar/1"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "Libro reservado, acuda a la biblioteca a recogerlo."))
			.andExpect(view().name("libros/listLibro"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testReservarLibroNoDisponible() throws Exception {
		mockMvc.perform(get("/libros/reservar/2"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "Libro no disponible"))
			.andExpect(view().name("libros/listLibro"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testReservarLibroNoExistente() throws Exception {
		mockMvc.perform(get("/libros/reservar/3"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "Libro no existente"))
			.andExpect(view().name("libros/listLibro"));
	}
	
	@WithMockUser(value = "Us3r2")
	@Test
	void testReservarLibroEnPrestamo() throws Exception {
		mockMvc.perform(get("/libros/reservar/1"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "Ya tienes ese libro en préstamo"))
			.andExpect(view().name("libros/listLibro"));
	}
}
