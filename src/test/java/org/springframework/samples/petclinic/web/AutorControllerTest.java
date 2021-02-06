package org.springframework.samples.petclinic.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Autor;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.service.AutorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers=AutorController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class AutorControllerTest {

	
	
	@Autowired
	AutorController controller;
	
	@MockBean
	AutorService autorService;

	@Autowired
	private MockMvc mockMvc;
	
	
	
	@WithMockUser(value = "Us3r")
	@Test
	void testAutoresList() throws Exception {
		mockMvc.perform(get("/autores"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("autores"))
			.andExpect(view().name("autores/listAutor"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testGuardarAutorSuccess() throws Exception {
		mockMvc.perform(post("/autores/save")
				.with(csrf())
				.param("nombre", "Federico")
				.param("apellidos", "Garcia Lorca")
				.param("fecha_nac", "05/06/1898"))
		.andExpect(status().isOk())
		.andExpect(view().name("autores/listAutor"));
				
		
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testGuardarAutorWithErrors() throws Exception {
		mockMvc.perform(post("/autores/save")
				.with(csrf())
				.param("nombre", "")
				.param("apellidos", "Garcia Lorca")
				.param("fecha_nac", "05/06/1898"))
		.andExpect(model().attributeHasErrors("autor"))
		.andExpect(status().isOk())
		.andExpect(view().name("autores/editAutor"));
				
		
	}

	@WithMockUser(value = "Us3r")
	@Test
	void testPublicarAutor() throws Exception {
		mockMvc.perform(get("/autores/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("autores/editAutor"))
		.andExpect(model().attributeExists("autor"));
	}
	
	
	
	@WithMockUser(value = "Us3r")
	@Test
	void testverLibrosAutor() throws Exception {
		Autor autor = new Autor();
		autor.setId(1);
		autor.setApellidos("A A");
		autor.setFecha_nac(LocalDate.now().minusDays(1));
		autor.setNombre("Pepe");
		autor.setLibros(new ArrayList<Libro>());
		given(this.autorService.findById(1)).willReturn(Optional.of(autor));
		mockMvc.perform(get("/autores/{autorId}",1))
			.andExpect(status().isOk())
			.andExpect(view().name("autores/librosAutor"))
			.andExpect(model().attributeExists("autor"))
			.andExpect(model().attributeExists("libros"));
		
	}
}
