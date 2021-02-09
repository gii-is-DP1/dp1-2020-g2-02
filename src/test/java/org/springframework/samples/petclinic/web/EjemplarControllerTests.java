package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
import org.springframework.samples.petclinic.model.Bibliotecario;
import org.springframework.samples.petclinic.model.Disponibilidad;
import org.springframework.samples.petclinic.model.Ejemplar;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.model.Miembro;
import org.springframework.samples.petclinic.model.Prestamo;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.EjemplarService;
import org.springframework.samples.petclinic.service.LibroService;
import org.springframework.samples.petclinic.service.PrestamoService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=EjemplarController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class EjemplarControllerTests {
	
	@Autowired
	EjemplarController controller;
	
	@MockBean
	EjemplarService ejemplarService;
	
	@MockBean
	LibroService libroService;
	
	@MockBean
	PrestamoService prestamoService;
	
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
		
		Ejemplar ejemplar = new Ejemplar();
		ejemplar.setId(1);
		ejemplar.setDisponibilidad(Disponibilidad.DISPONIBLE);
		ejemplar.setEstado("Primera página arrancada.");
		ejemplar.setLibro(new Libro());
		
		Ejemplar ejemplar2 = new Ejemplar();
		ejemplar2.setId(2);
		ejemplar2.setDisponibilidad(Disponibilidad.EN_PRESTAMO);
		ejemplar2.setEstado("Primera página arrancada.");
		ejemplar2.setLibro(new Libro());
		
		Prestamo prestamo1 = new Prestamo();
		prestamo1.setBibliotecario(new Bibliotecario());
		prestamo1.setEjemplar(ejemplar2);
		prestamo1.setFinalizado(false);
		prestamo1.setId(1);
		prestamo1.setFechaPrestamo(LocalDate.now());
		prestamo1.setFechaDevolucion(LocalDate.of(2020, 02, 21));
		
		
		
		
		//given(this.prestamoService.realizarReserva(1, miembro)).willReturn(prestamo1);
		given(this.ejemplarService.findById(1)).willReturn(Optional.of(ejemplar));
		given(this.ejemplarService.findById(2)).willReturn(Optional.of(ejemplar2));
		given(this.prestamoService.findById(1)).willReturn(Optional.of(prestamo1));
		//given(this.bibliotecarioService.save(bibliotecario));
		
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/ejemplares/new"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("ejemplar"))
		.andExpect(view().name("ejemplares/editEjemplar"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/ejemplares/save")
				.with(csrf())
				.param("titulo", "A")
				.param("estado", "A"))
			.andExpect(model().attribute("message", "Ejemplar guardado correctamente"))
			.andExpect(view().name("ejemplares/listEjemplar"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/ejemplares/save")
						.with(csrf())
						.param("titulo", "P"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("ejemplar"))
			.andExpect(model().attributeHasFieldErrors("ejemplar", "estado"))
			.andExpect(view().name("ejemplares/editEjemplar"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormDispErronea() throws Exception {
		mockMvc.perform(post("/ejemplares/save")
						.with(csrf())
						.param("titulo", "A")
						.param("estado", "A").param("disponibilidad", "aaaa"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("ejemplar"))
			.andExpect(model().attributeHasFieldErrors("ejemplar", "disponibilidad"))
			.andExpect(view().name("ejemplares/editEjemplar"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testDescatalogarEjemplarSuccess() throws Exception {
		mockMvc.perform(get("/ejemplares/descatalogar/{ejemplarId}",1))
		.andExpect(status().isOk())
		.andExpect(view().name("ejemplares/listEjemplar"))
		.andExpect(model().attributeExists("ejemplares"));
		
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testDescatalogarEjemplarHasErrors() throws Exception {
		mockMvc.perform(get("/ejemplares/descatalogar/{ejemplarId}",5))
		.andExpect(status().isOk())
		.andExpect(view().name("ejemplares/listEjemplar"))
		.andExpect(model().attribute("message","Ejemplar no encontrado"));
		
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testDescatalogarEjemplarEnPrestamo() throws Exception {
		mockMvc.perform(get("/ejemplares/descatalogar/{ejemplarId}",2))
		.andExpect(status().isOk())
		.andExpect(view().name("ejemplares/listEjemplar"))
		.andExpect(model().attribute("message","No se puede descatalogar el ejemplar: Se encuentra en un préstamo actualmente."));
		
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testInitEditFormNotFound() throws Exception {
        mockMvc.perform(get("/ejemplares/edit/{ejemplarId}",99))
        .andExpect(status().isOk())
        .andExpect(view().name("ejemplares/listEjemplar"))
        .andExpect(model().attribute("message","Ejemplar no encontrado"));
        
    }
    
    @WithMockUser(value = "Us3r")
    @Test
    void testProcessEditFormSuccess() throws Exception {
        mockMvc.perform(post("/ejemplares/saveEstado")
                .with(csrf())
                .param("ejemplar.id", "1")
                .param("estado", "A"))
            .andExpect(model().attribute("message", "Estado del ejemplar modificado correctamente"))
            .andExpect(view().name("ejemplares/listEjemplar"));
        
    }
    
    @WithMockUser(value = "Us3r")
    @Test
    void testProcessEditFormHasErrors() throws Exception {
        mockMvc.perform(post("/ejemplares/saveEstado")
                .with(csrf())
                .param("ejemplar.id", "1"))
            .andExpect(view().name("ejemplares/editEstado"));
    }
	
	

}
