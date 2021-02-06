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
import org.springframework.samples.petclinic.service.DatosDiariosService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=EstadisticasController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class EstadisticasControllerTest {

	@Autowired
	private EstadisticasController controller;
	
	@MockBean
	private DatosDiariosService datosService;
	
	@Autowired
	private MockMvc mockMvc;
	

	@WithMockUser(value = "Us3r")
	@Test
	void testEstadisticasList() throws Exception {
		mockMvc.perform(get("/estadisticas"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("datosDiarios"))
			.andExpect(view().name("datosDiarios/listDatos"));
	}
}
