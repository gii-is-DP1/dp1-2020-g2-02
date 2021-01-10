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
import org.springframework.samples.petclinic.service.EncargoService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=EjemplarController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class EncargoControllerTests {
	
	@Autowired
	EncargoController controller;
	
	@MockBean
	EncargoService encargoService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(value = "Us3r")
    @Test
    void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/encargos/new"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("encargo"))
		.andExpect(view().name("encargos/editEncargo"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/encargos/save").param("fecha_realizacion", "2020-11-11").param("fecha_entrega", "2020-12-12")
						.with(csrf()))
			.andExpect(model().attribute("message", "Encargo guardado correctamente"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/encargos/save")
						.with(csrf())
						.param("fecha_realizacion", "2020-11-11"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("encargo"))
			.andExpect(model().attributeHasFieldErrors("encargo", "fecha_entrega"))
			.andExpect(view().name("encargos/editEncargo"));
	}

}