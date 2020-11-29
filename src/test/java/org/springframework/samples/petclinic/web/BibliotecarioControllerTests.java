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
import org.springframework.samples.petclinic.service.BibliotecarioService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=BibliotecarioController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class BibliotecarioControllerTests {
	
	@Autowired
	BibliotecarioController controller;
	
	@MockBean
	BibliotecarioService bibliotecarioService;
	
	@MockBean
	UserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(value = "Us3r")
	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/bibliotecarios/new"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("bibliotecario"))
		.andExpect(view().name("bibliotecarios/editBibliotecario"));
	}
	
	/*@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/biliotecarios/save").param("nombre", "Natalia").param("apellidos", "Cabeza Ramirez")
						.param("dni", "49486598Q").param("telefono", "650606789").param("email", "nacara12@gmail.com")
						.param("username", "nacara").with(csrf()))
			.andExpect(model().attribute("message", "Bibliotecario guardada correctamente."));
	}*/
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/bibliotecarios/save")
						.with(csrf())
						.param("nombre", "P"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("bibliotecario"))
			.andExpect(model().attributeHasFieldErrors("bibliotecario", "apellidos"))
			.andExpect(view().name("bibliotecarios/editBibliotecario"));
	}

}
