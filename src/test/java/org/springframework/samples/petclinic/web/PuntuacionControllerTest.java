package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
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
import org.springframework.samples.petclinic.model.Autor;
import org.springframework.samples.petclinic.model.DatosDiarios;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.model.Miembro;
import org.springframework.samples.petclinic.model.Puntuacion;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AutorService;
import org.springframework.samples.petclinic.service.LibroService;
import org.springframework.samples.petclinic.service.MiembroService;
import org.springframework.samples.petclinic.service.PuntuacionService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=PuntuacionController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class PuntuacionControllerTest {

	private static final int TEST_LIBRO_ID=1;
	
	@Autowired
	PuntuacionController controller;
	
	@MockBean
	PuntuacionService puntuacionService;
	
	@MockBean
 	UserService userService;
	
	@MockBean
 	MiembroService miembroService;
	
	@MockBean
 	LibroService libroService;
	
	@MockBean
 	PrestamoController prestamoController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
	
		Libro libro = new Libro();
		libro.setId(1);
		libro.setISBN("1234567890");
		libro.setTitulo("Prueba");
		libro.setIdioma("Prueba");

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
		
		Puntuacion puntuacion = new Puntuacion();
		puntuacion.setId(1);
		puntuacion.setMiembro(miembro);
		puntuacion.setLibro(libro);
		
		given(this.libroService.findById(TEST_LIBRO_ID)).willReturn(Optional.of(libro));
		given(this.miembroService.findById(1)).willReturn(Optional.of(miembro));
		given(this.puntuacionService.findById(1)).willReturn(Optional.of(puntuacion));
		
	}
	
	@WithMockUser(value="Us3r")
	@Test
	void testInitCreationForm() throws Exception {
		
		mockMvc.perform(get("/puntuacion/valorar/{libroId}", TEST_LIBRO_ID))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("libro"))
			.andExpect(model().attributeExists("puntuacion"))
			.andExpect(view().name("libros/valorarLibro"));
	}

	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/puntuacion/save").param("puntaje", "4").param("libro", "3")
			.with(csrf()))
			.andExpect(model().attribute("message", "Libro valorado correctamente"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessUpdateFormSuccess() throws Exception {
		mockMvc.perform(post("/puntuacion/save").param("puntaje", "4")
				.param("libro", "1").param("autor", "1")
			.with(csrf()))
			.andExpect(model().attribute("message", "Valoración actualizada correctamente"));
	}
	
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormPuntajeFueraDeRango() throws Exception {
		mockMvc.perform(post("/puntuacion/save").param("puntaje", "8").param("libro", "3")
			.with(csrf()))
			.andExpect(model().attribute("message", "Hay fallos en el formulario"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormLibroNoPrestado() throws Exception {
		mockMvc.perform(post("/puntuacion/save").param("puntaje", "4").param("libro", "3")
				.param("miembro", "1")
			.with(csrf()))
			.andExpect(model().attribute("message", "Este libro no ha sido prestado por usted anteriormente"));
	}
	
}
