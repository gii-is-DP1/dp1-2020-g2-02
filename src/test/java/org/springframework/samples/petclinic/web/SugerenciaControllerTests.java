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
import org.springframework.samples.petclinic.service.MiembroService;
import org.springframework.samples.petclinic.service.SugerenciaService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(controllers=SugerenciaController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class SugerenciaControllerTests {
	
	@Autowired
	SugerenciaController controller;
	
	@MockBean
	SugerenciaService sugerenciaService;
	
	@MockBean
	MiembroService miembroService;
		
	@MockBean
 	UserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@WithMockUser(value = "Us3r")
	@Test
	void testSugerenciaList() throws Exception {
		mockMvc.perform(get("/sugerencias"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("sugerencias"))
			.andExpect(view().name("sugerencias/listSugerencia"));
	}
	
	
	@WithMockUser(value = "Us3r")
    @Test
    void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/sugerencias/new"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("sugerencia"))
			.andExpect(view().name("sugerencias/editSugerencia"));
	}
	
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/sugerencias/save")
				.param("titulo_libro", "A")
				.param("nombre_autor", "A")
			.with(csrf()))
			.andExpect(model().attribute("message", "Sugerencia guardada correctamente"));
	}
	
	
//	@WithMockUser(value = "Us3r")
//    @Test
//    void testProcessCreationFormHasErrors() throws Exception {
//		mockMvc.perform(post("/sugerencias/save")
//						.param("titulo_libro", "Prueba")
//			.with(csrf()))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("sugerencia"))
//			.andExpect(model().attributeHasFieldErrors("sugerencia","nombre_autor"))
//			.andExpect(view().name("sugerencias/editSugerencia"));
//	}

}
