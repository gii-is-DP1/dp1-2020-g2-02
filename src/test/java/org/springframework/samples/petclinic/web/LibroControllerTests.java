package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Miembro;
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
		
		User usuario2 = new User();
		usuario.setUsername("alecasgar2");
		usuario.setPassword("Ale123456");
		usuario.setEnabled(true);
	
		
		Miembro miembro2 = new Miembro();
		miembro.setId(1);
		miembro.setNombre("Alejandro");
		miembro.setApellidos("Castro Garcia");
		miembro.setDni("49586958D");
		miembro.setTelefono("123456789");
		miembro.setEmail("alecagar@gmail.com");
		miembro.setUser(usuario);
		
		User usuario3 = new User();
		usuario.setUsername("alecasgar3");
		usuario.setPassword("Ale123456");
		usuario.setEnabled(true);
	
		
		Miembro miembro3 = new Miembro();
		miembro.setId(1);
		miembro.setNombre("Alejandro");
		miembro.setApellidos("Castro Garcia");
		miembro.setDni("49586958D");
		miembro.setTelefono("123456789");
		miembro.setEmail("alecagar@gmail.com");
		miembro.setUser(usuario);
		
		given(this.userService.findByUsername("alecasgar")).willReturn(usuario);
		given(this.miembroService.findByUser(usuario)).willReturn(miembro);
		
		given(this.userService.findByUsername("alecasgar2")).willReturn(usuario2);
		given(this.miembroService.findByUser(usuario2)).willReturn(miembro2);
		
		given(this.userService.findByUsername("alecasgar3")).willReturn(usuario3);
		given(this.miembroService.findByUser(usuario3)).willReturn(miembro3);
		
		given(prestamoService.realizarReserva(1,miembro)).willReturn(new Prestamo());
		given(prestamoService.realizarReserva(2,miembro)).willThrow(new LibroNoExistenteException());
		given(prestamoService.realizarReserva(3,miembro)).willThrow(new LibroNoDisponibleException());
		given(prestamoService.realizarReserva(4,miembro)).willThrow(new LibroYaEnPrestamoException());
		given(prestamoService.realizarReserva(1,miembro2)).willThrow(new LimitePrestamosException());
		given(prestamoService.realizarReserva(2,miembro3)).willThrow(new PrestamoConRetrasoException());
		
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
	void testReservarLibroNoExistente() throws Exception {
		mockMvc.perform(get("/libros/reservar/2"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "Libro no existente"))
			.andExpect(view().name("libros/listLibro"));
	}
	@WithMockUser(value = "alecasgar")
	@Test
	void testReservarLibroNoDisponible() throws Exception {
		mockMvc.perform(get("/libros/reservar/3"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "Libro no disponible"))
			.andExpect(view().name("libros/listLibro"));
	}
	@WithMockUser(value = "alecasgar")
	@Test
	void testReservarLibroYaEnPrestamo() throws Exception {
		mockMvc.perform(get("/libros/reservar/4"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "Ya tienes ese libro en préstamo"))
			.andExpect(view().name("libros/listLibro"));
	}
	@WithMockUser(value = "alecasgar2")
	@Test
	void testReservarLibroLimitePrestamo() throws Exception {
		mockMvc.perform(get("/libros/reservar/1"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "Ha superado el limite de préstamos, por favor entregue uno de sus préstamos antes de realizar uno nuevo"))
			.andExpect(view().name("libros/listLibro"));
	}
	@WithMockUser(value = "alecasgar3")
	@Test
	void testReservarLibroPrestamoConRetraso() throws Exception {
		mockMvc.perform(get("/libros/reservar/2"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "Por favor, entregue su préstamo pendiente de devolución antes de realizar uno nuevo"))
			.andExpect(view().name("libros/listLibro"));
	}

	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/libros/save")
				.param("ISBN", "1111111111")
				.param("titulo", "Titulo Prueba")
				.param("Idioma", "Idioma Prueba")
				.param("fechaPublicacion", "01/01/2000")
			.with(csrf()))
			.andExpect(view().name("libros/listLibro"))
			.andExpect(model().attribute("message", "Libro guardado correctamente"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormISBNErroneo() throws Exception {
		mockMvc.perform(post("/libros/save")
				.param("ISBN", "1")
				.param("titulo", "Titulo Prueba")
				.param("Idioma", "Idioma Prueba")
				.param("fecha_publicacion", "01/01/2000")
			.with(csrf()))
			.andExpect(view().name("libros/editLibro"))
			.andExpect(model().attributeHasErrors("libro"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormFechaPosterior() throws Exception {
		mockMvc.perform(post("/libros/save")
				.param("ISBN", "1111111111")
				.param("titulo", "Titulo Prueba")
				.param("Idioma", "Idioma Prueba")
				.param("fecha_publicacion", "01/01/2200")
			.with(csrf()))
			.andExpect(view().name("libros/editLibro"))
			.andExpect(model().attributeHasErrors("libro"));
	}
}