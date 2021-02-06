package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.mapping.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Bibliotecario;
import org.springframework.samples.petclinic.model.DatosDiarios;
import org.springframework.samples.petclinic.model.Disponibilidad;
import org.springframework.samples.petclinic.model.Ejemplar;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.model.Miembro;
import org.springframework.samples.petclinic.model.Prestamo;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.DatosDiariosService;
import org.springframework.samples.petclinic.service.EncargoService;
import org.springframework.samples.petclinic.service.MiembroService;
import org.springframework.samples.petclinic.service.PrestamoService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=WelcomeController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class WelcomeControllerTest {

	@Autowired
	WelcomeController controller;
	
	@MockBean
    UserService userService;
    
	@MockBean
    MiembroService miembroService;
    
	@MockBean
    PrestamoService prestamoService;
    
	@MockBean
    EncargoService encargoService;

	@MockBean
    DatosDiariosService datosService;

	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
	
		User usuario = new User();
		usuario.setUsername("alecasgar");
		usuario.setPassword("Ale123456");
		usuario.setEnabled(true);
	
		
		Miembro miembro = new Miembro();
		miembro.setId(1);
		miembro.setNombre("Alejandro");
		miembro.setApellidos("Castro Garcia");
		miembro.setDni("49586958D");
		miembro.setTelefono("123456789");
		miembro.setEmail("alecagar@gmail.com");
		miembro.setUser(usuario);
		
		DatosDiarios datosDiarios = new DatosDiarios();
		datosDiarios.setFecha(LocalDate.now());
		datosDiarios.setId(1);
		DatosDiarios datosDiarios2 = new DatosDiarios();
		datosDiarios2.setFecha(LocalDate.of(2020, 01, 01));
		datosDiarios2.setId(2);
		List<DatosDiarios> dd = new ArrayList<>();
		dd.add(datosDiarios);dd.add(datosDiarios2);
		
		given(this.datosService.findAllOrderByFecha()).willReturn(dd);
		
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testWelcome() throws Exception {
		mockMvc.perform(get("/welcome"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("datos"))
			.andExpect(model().attributeExists("persons"))
			.andExpect(model().attributeExists("title"))
			.andExpect(model().attributeExists("group"))
			.andExpect(view().name("welcome"));
	}
}
