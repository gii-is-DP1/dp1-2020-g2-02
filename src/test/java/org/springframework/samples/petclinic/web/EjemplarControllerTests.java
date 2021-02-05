package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
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
import org.springframework.samples.petclinic.model.Novedad;
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
	
		Ejemplar ejemplar = new Ejemplar();
		ejemplar.setId(1);
		ejemplar.setDisponibilidad(Disponibilidad.DISPONIBLE);
		ejemplar.setEstado("Primera página arrancada.");
		ejemplar.setLibro(new Libro());
		
		
		given(this.ejemplarService.findById(1)).willReturn(Optional.of(ejemplar));
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
			.andExpect(view().name("ejemplares/listEjemplar"));;
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
	
	

}
