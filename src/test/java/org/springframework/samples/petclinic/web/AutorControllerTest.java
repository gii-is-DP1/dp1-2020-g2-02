package org.springframework.samples.petclinic.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.service.AutorService;
import org.springframework.samples.petclinic.service.LibroService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=AutorController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class AutorControllerTest {

	@Autowired
	AutorController controller;
	
	@MockBean
	AutorService autorService;
	
	@MockBean
	LibroService libroService;

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
	
	/*@WithMockUser(value = "Us3r")
	@Test
	void testverLibrosAutor() throws Exception {
		mockMvc.perform(get("/autores/{autorId}",1))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("autor"))
			.andExpect(model().attributeExists("libros"))
			.andExpect(view().name("autores/librosAutor"));
	}*/
}
