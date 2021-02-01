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
import org.springframework.samples.petclinic.service.EjemplarService;
import org.springframework.samples.petclinic.service.LibroService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=EjemplarController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class EjemplarControllerTests {
	
	@Autowired
	EjemplarController controller;
	
	@MockBean
	EjemplarService ejemplarService;
	
	@MockBean
	LibroService libroService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(value = "Us3r")
    @Test
    void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/ejemplares/new"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("ejemplar"))
		.andExpect(view().name("ejemplares/editEjemplar"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/ejemplares/save").param("titulo", "A").param("estado", "A")
						.with(csrf()))
			.andExpect(model().attribute("message", "Ejemplar guardado correctamente"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/ejemplares/save")
						.with(csrf())
						.param("titulo", "P"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("ejemplar"))
			.andExpect(model().attributeHasFieldErrors("ejemplar", "estado"))
			.andExpect(view().name("ejemplares/editEjemplar"));
	}

}
