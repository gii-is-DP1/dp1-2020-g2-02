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
import org.springframework.samples.petclinic.service.AutorService;
import org.springframework.samples.petclinic.service.EditorialService;
import org.springframework.samples.petclinic.service.EjemplarService;
import org.springframework.samples.petclinic.service.GeneroService;
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
	
	@MockBean
	AutorService autorService;
	
	@MockBean
	EditorialService editorialService;
	
	@MockBean
	GeneroService generoService;
	
	@Autowired
	private MockMvc mockMvc;
	
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
}
