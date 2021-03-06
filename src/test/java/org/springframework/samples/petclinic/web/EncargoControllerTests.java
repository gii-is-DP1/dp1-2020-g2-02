package org.springframework.samples.petclinic.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Encargo;
import org.springframework.samples.petclinic.model.Libro;
import org.springframework.samples.petclinic.service.CantidadService;
import org.springframework.samples.petclinic.service.EncargoService;
import org.springframework.samples.petclinic.service.LibroService;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.samples.petclinic.service.exceptions.LimiteEjemplaresException;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers=EncargoController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class EncargoControllerTests {
	
	@Autowired
	EncargoController controller;
	
	@MockBean
	EncargoService encargoService;
	
	@MockBean
	ProveedorService proveedorService;
	
	@MockBean
	LibroService libroService;
	
	@MockBean
	CantidadService cantidadService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		Libro libro = new Libro();
		libro.setId(1);
		
		given(libroService.findById(1)).willReturn(Optional.of(libro));
	}
	
	
	@WithMockUser(value = "Us3r")
	@Test
	void testEncargosList() throws Exception {
		mockMvc.perform(get("/encargos"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("encargos"))
			.andExpect(view().name("encargos/listEncargo"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testInitCreationForm() throws Exception {
			mockMvc.perform(get("/encargos/new"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("encargo"))
			.andExpect(view().name("encargos/editEncargo"));
	}

	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/encargos/save").param("fechaRealizacion", "11/11/2022")
				.param("fechaEntrega", "12/12/2200").param("guardar", "true")
				.with(csrf()))
			.andExpect(model().attribute("message", "Encargo guardado correctamente"));

	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testDemasiadosEjemplares() throws Exception {
		Mockito.doThrow(new LimiteEjemplaresException()).when(encargoService).save(any(Encargo.class));
		mockMvc.perform(post("/encargos/save").param("fechaRealizacion", "11/11/2022")
				.param("fechaEntrega", "12/12/2200").param("guardar", "true")
				.with(csrf()))
			.andExpect(model().attribute("message", "Demasiados ejemplares del libro"));

	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testAddCantidad() throws Exception {
		mockMvc.perform(post("/encargos/save").param("fechaRealizacion", "11/11/2022")
				.param("fechaEntrega", "12/12/2200")
				.param("libroEncargo","1")
				.param("numEjemplares","1")
				.param("precioUnitario","1")
				.param("addLibro", "true")
				.with(csrf()))
			.andExpect(model().attribute("message", "Añadido libro al pedido."));

	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testAddCantidadErrorCantidadInvalida() throws Exception {
		mockMvc.perform(post("/encargos/save").param("fechaRealizacion", "11/11/2022")
				.param("fechaEntrega", "12/12/2200")
				.param("libroEncargo","1")
				.param("numEjemplares","cantidad invalida")
				.param("precioUnitario","1.2")
				.param("addLibro", "true")
				.with(csrf()))
			.andExpect(model().attribute("message", "Error al añadir libros al pedido."));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testAddCantidadErrorPrecioInvalido() throws Exception {
		mockMvc.perform(post("/encargos/save").param("fechaRealizacion", "11/11/2022")
				.param("fechaEntrega", "12/12/2200")
				.param("libroEncargo","1")
				.param("numEjemplares","1")
				.param("precioUnitario","precio invalido")
				.param("addLibro", "true")
				.with(csrf()))
			.andExpect(model().attribute("message", "Error al añadir libros al pedido."));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testAddCantidadErrorLibroNoExistente() throws Exception {
		mockMvc.perform(post("/encargos/save").param("fechaRealizacion", "11/11/2022")
				.param("fechaEntrega", "12/12/2200")
				.param("libroEncargo","2")
				.param("numEjemplares","1")
				.param("precioUnitario","1.2")
				.param("addLibro", "true")
				.with(csrf()))
			.andExpect(model().attribute("message", "Error al añadir libros al pedido."));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/encargos/save")
						.with(csrf())
						.param("guardar", "true")
						.param("fechaRealizacion", "11/11/2020"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("encargo"))
			.andExpect(model().attributeHasFieldErrors("encargo", "fechaEntrega"))
			.andExpect(view().name("encargos/editEncargo"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormFechaDeEntregaErronea() throws Exception {
		
		mockMvc.perform(post("/encargos/save")
						.with(csrf())
						.param("guardar", "true")
						.param("fechaRealizacion", "11-11-2020")
						.param("fechaEntrega", "7/12/2010"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("encargo"))
			.andExpect(model().attributeHasFieldErrors("encargo", "fechaEntrega"))
			.andExpect(view().name("encargos/editEncargo"));
	}
	
	

}