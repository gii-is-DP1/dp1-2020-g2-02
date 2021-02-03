package org.springframework.samples.petclinic.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.samples.petclinic.service.LibroService;
import org.springframework.samples.petclinic.service.MiembroService;
import org.springframework.samples.petclinic.service.PuntuacionService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=PuntuacionController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class PuntuacionControllerTest {

	@Autowired
	PuntuacionController controller;
	
	@MockBean
	PuntuacionService puntuacionService;
	
	@MockBean
 	MiembroService miembroService;
	
	@MockBean
 	LibroService librosService;
	
	@MockBean
 	UserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(value="Us3r")
	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/puntuacion/valorar/{libroId}", 3))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("libro"))
			.andExpect(model().attributeExists("puntuacion"))
			.andExpect(view().name("libros/valorarLibro"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/puntuacion/save").param("puntaje", "4").param("libro", "3")
			.with(csrf()))
			.andExpect(model().attribute("message", "Libro valorado correctamente"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/puntuacion/save").param("puntaje", "8").param("libro", "3")
			.with(csrf()))
			.andExpect(model().attribute("message", "Hay fallos en el formulario"));
	}
	
}
