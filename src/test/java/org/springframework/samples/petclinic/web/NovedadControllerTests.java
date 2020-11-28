package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
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
import org.springframework.samples.petclinic.service.BibliotecarioService;
import org.springframework.samples.petclinic.service.NovedadService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=NovedadController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class NovedadControllerTests {

	@Autowired
	NovedadController controller;
	
	@MockBean
	BibliotecarioService bibliotecarioService;
	
	@MockBean
	NovedadService novedadService;
	
	@MockBean
	UserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(value = "Us3r")
    	@Test
    void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/novedades/new"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("novedad"))
		.andExpect(view().name("novedades/editNovedad"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/novedades/save").param("titulo", "A").param("contenido", "A")
						.with(csrf()))
			.andExpect(model().attribute("message", "Novedad guardada correctamente."));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/novedades/save")
						.with(csrf())
						.param("titulo", "Prueba"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("novedad"))
			.andExpect(model().attributeHasFieldErrors("novedad", "contenido"))
			.andExpect(view().name("novedades/editNovedad"));
	}
}
