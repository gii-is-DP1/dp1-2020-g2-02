package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Bibliotecario;
import org.springframework.samples.petclinic.model.Miembro;
import org.springframework.samples.petclinic.model.Novedad;
import org.springframework.samples.petclinic.model.Prestamo;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AutorService;
import org.springframework.samples.petclinic.service.EditorialService;
import org.springframework.samples.petclinic.service.EjemplarService;
import org.springframework.samples.petclinic.service.GeneroService;
import org.springframework.samples.petclinic.service.LibroService;
import org.springframework.samples.petclinic.service.MiembroService;
import org.springframework.samples.petclinic.service.PrestamoService;
import org.springframework.samples.petclinic.service.UserService;

import org.springframework.samples.petclinic.service.exceptions.LibroNoDisponibleException;
import org.springframework.samples.petclinic.service.exceptions.LibroNoExistenteException;
import org.springframework.samples.petclinic.service.exceptions.LibroYaEnPrestamoException;
import org.springframework.samples.petclinic.service.exceptions.LimitePrestamosException;
import org.springframework.samples.petclinic.service.exceptions.PrestamoConRetrasoException;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=LibroController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class LibroControllerTests {
	
	@BeforeEach
    void setup() throws Exception {
        
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
        
        given(this.userService.findByUsername("alecasgar")).willReturn(usuario);
        given(this.miembroService.findByUser(usuario)).willReturn(miembro);
        given(prestamoService.realizarReserva(1,miembro)).willReturn(new Prestamo());
        given(prestamoService.realizarReserva(2,miembro)).willThrow(new LibroNoExistenteException());
        
    }

	@Autowired
	LibroController controller;
	
	@MockBean
	LibroService libroService;
	
	@MockBean
	MiembroService miembroService;
	
	@MockBean
	UserService userService;
	
	@MockBean
	EjemplarService ejemplarService;
	
	@MockBean
	PrestamoService prestamoService;
	
	@MockBean
	AutorService autorService;
	
	@MockBean
	EditorialService editorialService;
	
	@MockBean
	GeneroService generoService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() throws Exception {
		
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
		
		given(this.userService.findByUsername("alecasgar")).willReturn(usuario);
		given(this.miembroService.findByUser(usuario)).willReturn(miembro);
		given(prestamoService.realizarReserva(1,miembro)).willReturn(new Prestamo());
		given(prestamoService.realizarReserva(2,miembro)).willThrow(new LibroNoExistenteException());
		
	}
	
	@WithMockUser(value = "alecasgar")
	@Test
	void testLibrosList() throws Exception {
		mockMvc.perform(get("/libros"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("libros"))
			.andExpect(view().name("libros/listLibro"));
	}
	@WithMockUser(value = "alecasgar")
	@Test
	void testReservarLibro() throws Exception {
		mockMvc.perform(get("/libros/reservar/1"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "Libro reservado, acuda a la biblioteca a recogerlo."))
			.andExpect(view().name("libros/listLibro"));
	}
	@WithMockUser(value = "alecasgar")
	@Test
	void testReservarLibroError() throws Exception {
		mockMvc.perform(get("/libros/reservar/2"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "Libro no existente"))
			.andExpect(view().name("libros/listLibro"));
	}
//	@WithMockUser(value = "Us3r")
//	@Test
//	void testLibrosList() throws Exception {
//		mockMvc.perform(get("/libros"))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeExists("libros"))
//			.andExpect(view().name("libros/listLibro"));
//	}
//	@WithMockUser(value = "Us3r")
//	@Test
//	void testReservarLibro() throws Exception {
//		mockMvc.perform(get("/libros/reservar/1"))
//			.andExpect(status().isOk())
//			.andExpect(model().attribute("message", "Libro reservado, acuda a la biblioteca a recogerlo."))
//			.andExpect(view().name("libros/listLibro"));
//	}
}
