package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.service.AutorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=BibliotecarioController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class AutorControllerTest {

	@Autowired
	AutorController controller;
	
	@MockBean
	AutorService autorService;

	@Autowired
	private MockMvc mockMvc;
}
