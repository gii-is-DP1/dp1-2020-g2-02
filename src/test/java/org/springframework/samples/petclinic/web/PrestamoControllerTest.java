package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.HashSet;
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
import org.springframework.samples.petclinic.model.Disponibilidad;
import org.springframework.samples.petclinic.model.Ejemplar;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.model.Miembro;
import org.springframework.samples.petclinic.model.Novedad;
import org.springframework.samples.petclinic.model.Prestamo;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AutorService;
import org.springframework.samples.petclinic.service.BibliotecarioService;
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

@WebMvcTest(controllers=PrestamoController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class PrestamoControllerTest {
	
	@Autowired
	PrestamoController controller;
	
	@MockBean
	MiembroService miembroService;
	
	@MockBean
	PrestamoService prestamoService;
	
	@MockBean
	UserService userService;
	
	@MockBean
	BibliotecarioService bibliotecarioService;
	
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
		
		User usuario4 = new User();
		usuario.setUsername("pepe5");
		usuario.setPassword("Ale123456");
		usuario.setEnabled(true);
		
		Bibliotecario bibliotecario = new Bibliotecario();
		bibliotecario.setId(1);
		bibliotecario.setNombre("Alejandro");
		bibliotecario.setApellidos("Castro Garcia");
		bibliotecario.setDni("49586958D");
		bibliotecario.setTelefono("123456789");
		bibliotecario.setEmail("alecagar@gmail.com");
		bibliotecario.setUser(usuario4);
		bibliotecario.setNovedades(new HashSet<Novedad>());
		
		Ejemplar ejemplar1 = new Ejemplar();
		ejemplar1.setId(1);
		ejemplar1.setLibro(new Libro());
		ejemplar1.setDisponibilidad(Disponibilidad.RESERVADO);
		ejemplar1.setEstado("Bueno");
		
		Ejemplar ejemplar2 = new Ejemplar();
		ejemplar2.setId(3);
		ejemplar2.setLibro(new Libro());
		ejemplar2.setDisponibilidad(Disponibilidad.RESERVADO);
		ejemplar2.setEstado("Bueno");
		
		Ejemplar ejemplar3 = new Ejemplar();
		ejemplar3.setId(3);
		ejemplar3.setLibro(new Libro());
		ejemplar3.setDisponibilidad(Disponibilidad.EN_PRESTAMO);
		ejemplar3.setEstado("Bueno");
		
		Prestamo prestamo1 = new Prestamo();
		prestamo1.setBibliotecario(new Bibliotecario());
		prestamo1.setEjemplar(ejemplar1);
		prestamo1.setFinalizado(false);
		prestamo1.setId(1);
		prestamo1.setFechaPrestamo(LocalDate.now());
		prestamo1.setFechaDevolucion(LocalDate.of(2020, 02, 21));
		
		Prestamo prestamo2 = new Prestamo();
		prestamo2.setBibliotecario(new Bibliotecario());
		prestamo2.setEjemplar(ejemplar2);
		prestamo2.setFinalizado(true);
		prestamo2.setId(2);
		prestamo2.setFechaPrestamo(LocalDate.now());
		prestamo2.setFechaDevolucion(LocalDate.of(2020, 02, 21));
		
		Prestamo prestamo3 = new Prestamo();
		prestamo3.setBibliotecario(new Bibliotecario());
		prestamo3.setEjemplar(ejemplar3);
		prestamo3.setFinalizado(false);
		prestamo3.setId(3);
		prestamo3.setFechaPrestamo(LocalDate.now());
		prestamo3.setFechaDevolucion(LocalDate.of(2020, 02, 21));
		
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
		
		given(prestamoService.findById(1)).willReturn(Optional.of(prestamo1));
		given(prestamoService.findById(2)).willReturn(Optional.of(prestamo2));
		given(prestamoService.findById(3)).willReturn(Optional.of(prestamo3));
		
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testPrestamosList() throws Exception {
		mockMvc.perform(get("/prestamos"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("prestamos"))
			.andExpect(view().name("prestamos/listPrestamo"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormPrestamoSuccess() throws Exception {
		mockMvc.perform(post("/prestamos/save")
				.param("fechaPrestamo", "2020-02-12")
				.param("finalizado", "false")
			.with(csrf()))
			.andExpect(view().name("prestamos/listPrestamo"))
			.andExpect(model().attribute("message", "Prestamo guardado correctamente"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormPrestamoHasErrors() throws Exception {
		mockMvc.perform(post("/prestamos/save")
				.param("fechaPrestamo", "")
				.param("finalizado", "")
			.with(csrf()))
			.andExpect(view().name("prestamos/editPrestamo"))
			.andExpect(model().attributeHasErrors("prestamo"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testPublicarEncargo() throws Exception {
		mockMvc.perform(get("/prestamos/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("prestamos/editPrestamo"))
		.andExpect(model().attributeExists("prestamo"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testConcederPrestamo() throws Exception {
		mockMvc.perform(get("/prestamos/conceder/{prestamoId}",1))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "Préstamo concedido correctamente."))
			.andExpect(view().name("prestamos/listPrestamo"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testConcederPrestamoNoExistente() throws Exception {
		mockMvc.perform(get("/prestamos/conceder/{prestamoId}",100))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "El préstamo no existe."))
			.andExpect(view().name("prestamos/listPrestamo"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testConcederPrestamoFinalizado() throws Exception {
		mockMvc.perform(get("/prestamos/conceder/{prestamoId}",2))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "El préstamo no se puede conceder (Ya ha finalizado o el ejemplar no se encuentra reservado)."))
			.andExpect(view().name("prestamos/listPrestamo"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testConcederPrestamoDeEjemplarEnPrestamo() throws Exception {
		mockMvc.perform(get("/prestamos/conceder/{prestamoId}",3))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "El préstamo no se puede conceder (Ya ha finalizado o el ejemplar no se encuentra reservado)."))
			.andExpect(view().name("prestamos/listPrestamo"));
	}
	
	@WithMockUser(value = "alecasgar")
	@Test
	void testPrestamosMiembroList() throws Exception {
		mockMvc.perform(get("/prestamos/misprestamos"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("prestamos"))
			.andExpect(view().name("prestamos/listPrestamoMiembro"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testFinalizarPrestamo() throws Exception {
		mockMvc.perform(get("/prestamos/finalizar/{prestamoId}",3))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "Préstamo finalizado correctamente."))
			.andExpect(view().name("prestamos/listPrestamo"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testFinalizarPrestamoNoExistente() throws Exception {
		mockMvc.perform(get("/prestamos/finalizar/{prestamoId}",22))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "El préstamo no existe."))
			.andExpect(view().name("prestamos/listPrestamo"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testFinalizarPrestamoYaFinalizado() throws Exception {
		mockMvc.perform(get("/prestamos/finalizar/{prestamoId}",2))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "El préstamo no se puede finalizar (Ya ha finalizado o el ejemplar no se encuentra en préstamo)."))
			.andExpect(view().name("prestamos/listPrestamo"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testFinalizarPrestamoQueNoSeEncuentraEnPrestamo() throws Exception {
		mockMvc.perform(get("/prestamos/finalizar/{prestamoId}",1))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "El préstamo no se puede finalizar (Ya ha finalizado o el ejemplar no se encuentra en préstamo)."))
			.andExpect(view().name("prestamos/listPrestamo"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testRechazarPrestamo() throws Exception {
		mockMvc.perform(get("/prestamos/rechazar/{prestamoId}",1))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "Préstamo rechazado correctamente."))
			.andExpect(view().name("prestamos/listPrestamo"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testRechazarPrestamoNoExistente() throws Exception {
		mockMvc.perform(get("/prestamos/rechazar/{prestamoId}",22))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "El préstamo no existe."))
			.andExpect(view().name("prestamos/listPrestamo"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testRechazarPrestamoYaFinalizado() throws Exception {
		mockMvc.perform(get("/prestamos/rechazar/{prestamoId}",2))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "El préstamo no se puede rechazar (Ya ha finalizado o el ejemplar no se encuentra reservado)."))
			.andExpect(view().name("prestamos/listPrestamo"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testRechazarPrestamoQueNoSeEncuentraReservado() throws Exception {
		mockMvc.perform(get("/prestamos/rechazar/{prestamoId}",3))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "El préstamo no se puede rechazar (Ya ha finalizado o el ejemplar no se encuentra reservado)."))
			.andExpect(view().name("prestamos/listPrestamo"));
	}
	
	

	
	
	
	

}
