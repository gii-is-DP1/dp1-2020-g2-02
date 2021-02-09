package org.springframework.samples.petclinic.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.DatosDiarios;
import org.springframework.samples.petclinic.model.Encargo;
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
		usuario.setUsername("usuarioMiembro");
		usuario.setEnabled(true);
	
		Set<Authorities> autoridades = new HashSet<>();
		Authorities autoridad = new Authorities();
		autoridad.setId(1);
		autoridad.setAuthority("miembro");
		autoridad.setUser(usuario);
		autoridades.add(autoridad);
		usuario.setAuthorities(autoridades);
		
		Miembro miembro = new Miembro();
		miembro.setId(1);
		miembro.setUser(usuario);
		
		User usuario2 = new User();
		usuario2.setUsername("usuarioBibliotecario");
		usuario2.setEnabled(true);
	
		Set<Authorities> autoridades2 = new HashSet<>();
		Authorities autoridad2 = new Authorities();
		autoridad2.setId(2);
		autoridad2.setAuthority("bibliotecario");
		autoridad2.setUser(usuario2);
		autoridades2.add(autoridad2);
		usuario2.setAuthorities(autoridades2);
		
		DatosDiarios datosDiarios = new DatosDiarios();
		datosDiarios.setFecha(LocalDate.now());
		datosDiarios.setId(1);
		List<DatosDiarios> dd = new ArrayList<>();
		dd.add(datosDiarios);
		
		given(this.datosService.findAllOrderByFecha()).willReturn(dd);
		
		given(this.userService.findByUsername("usuarioMiembro")).willReturn(usuario);
		given(this.miembroService.findByUser(usuario)).willReturn(miembro);
		
		given(this.userService.findByUsername("usuarioBibliotecario")).willReturn(usuario2);
		
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
	
	@WithMockUser(value = "usuarioMiembro")
	@Test
	void testInicioMiembro() throws Exception {
		mockMvc.perform(get("/inicio"))
			.andExpect(status().isOk())
			.andExpect(model().attributeDoesNotExist("prestamoUrgente"))
			.andExpect(model().attributeDoesNotExist("pedidoUrgente"))
			.andExpect(model().attribute("message", "Bienvenido, usuarioMiembro"))
			.andExpect(view().name("welcome"));
	}
	
	@WithMockUser(value = "usuarioMiembro")
	@Test
	void testInicioMiembroPrestamoUrgente() throws Exception {
		List<Prestamo> prestamos = new ArrayList<>();
		Prestamo p = new Prestamo();
		prestamos.add(p);
		given(prestamoService.prestamosMiembrosUrgentes(any(Miembro.class))).willReturn(prestamos);
		mockMvc.perform(get("/inicio"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("prestamoUrgente", true))
			.andExpect(model().attributeDoesNotExist("pedidoUrgente"))
			.andExpect(model().attribute("message", "Bienvenido, usuarioMiembro"))
			.andExpect(view().name("welcome"));
	}
	
	@WithMockUser(value = "usuarioBibliotecario")
	@Test
	void testInicioBibliotecario() throws Exception {
		mockMvc.perform(get("/inicio"))
			.andExpect(status().isOk())
			.andExpect(model().attributeDoesNotExist("prestamoUrgente"))
			.andExpect(model().attributeDoesNotExist("pedidoUrgente"))
			.andExpect(model().attribute("message", "Bienvenido, usuarioBibliotecario"))
			.andExpect(view().name("welcome"));
	}
	
	@WithMockUser(value = "usuarioBibliotecario")
	@Test
	void testInicioBibliotecarioEncargoUrgente() throws Exception {
		List<Encargo> encargos = new ArrayList<>();
		Encargo e = new Encargo();
		encargos.add(e);
		given(encargoService.pedidosUrgentes()).willReturn(encargos);
		mockMvc.perform(get("/inicio"))
			.andExpect(status().isOk())
			.andExpect(model().attributeDoesNotExist("prestamoUrgente"))
			.andExpect(model().attribute("pedidoUrgente", true))
			.andExpect(model().attribute("message", "Bienvenido, usuarioBibliotecario"))
			.andExpect(view().name("welcome"));
	}
}
