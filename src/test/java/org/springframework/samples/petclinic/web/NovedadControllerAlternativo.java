package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Novedad;
import org.springframework.samples.petclinic.service.NovedadService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=NovedadControladorAlternativo.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class NovedadControllerAlternativo {

	private static final int TEST_NOVEDAD_ID=1;
	
	@Autowired
	NovedadControladorAlternativo controller;
	
	@MockBean
	NovedadService novedadService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
	
		Novedad novedad = new Novedad();
		novedad.setTitulo("prueba");
		novedad.setId(TEST_NOVEDAD_ID);
		novedad.setContenido("prueba");
		novedad.setFechaPublicacion(LocalDate.now());
		
		given(this.novedadService.findById(TEST_NOVEDAD_ID)).willReturn(Optional.of(novedad));
		
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testListNovedades() throws Exception {
		mockMvc.perform(get("/api/novedades"))
			.andExpect(status().isOk())
			.andExpect(view().name("novedades/novedadesAPI"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testListNovedad() throws Exception {
		mockMvc.perform(get("/api/novedades/{id}",TEST_NOVEDAD_ID))
			.andExpect(status().isOk())
			.andExpect(view().name("novedades/novedadesAPI"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testListNovedadesHoy() throws Exception {
		mockMvc.perform(get("/api/novedades/Today"))
			.andExpect(status().isOk())
			.andExpect(view().name("novedades/novedadesAPI"));
	}
}
