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
import org.springframework.samples.petclinic.model.Editorial;
import org.springframework.samples.petclinic.service.EditorialService;
import org.springframework.samples.petclinic.service.LibroService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=EditorialController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class EditorialControllerTest {

	@Autowired
	EditorialController controller;
	@MockBean
	EditorialService editorialService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
	
		Editorial editorial = new Editorial();
		editorial.setId(1);
		editorial.setNombre("Planeta");
		editorial.setNif("A1231123B");
		editorial.setDireccion("C/ Jerez Alta Nº12, Sevilla, España");
		editorial.setTelefono("123456789");
		editorial.setEmail("planeta@gmail.com");
		editorial.setWeb("www.planeta.com");
		
		
		
		given(this.editorialService.findById(1)).willReturn(Optional.of(editorial));
		
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testEditorialesList() throws Exception {
		mockMvc.perform(get("/editoriales"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("editoriales"))
			.andExpect(view().name("editoriales/listEditorial"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testProcessCreationFormEditorialSuccess() throws Exception {
		mockMvc.perform(post("/editoriales/save")
				.with(csrf())
				.param("nombre", "Norma")
				.param("nif", "A1231123B")
				.param("direccion", "calle Ejemplo, 13, Barcelona")
				.param("telefono", "650666999")
				.param("email", "norma@mail.com")
				.param("web", "www.norma.com"))
		.andExpect(model().attribute("message", "Editorial guardada correctamente"))
		.andExpect(status().isOk())
		.andExpect(view().name("editoriales/listEditorial"));
				
		
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testProcessCreationFormEditorialWrongDNIF() throws Exception {
		mockMvc.perform(post("/editoriales/save")
				.with(csrf())
				.param("nombre", "Norma")
				.param("nif", "A123112389789798789789787yuiihlminuiB")
				.param("direccion", "calle Ejemplo, 13, Barcelona")
				.param("telefono", "650666999")
				.param("email", "norma@mail.com")
				.param("web", "www.norma.com"))
		.andExpect(model().attributeHasErrors("editorial"))
		.andExpect(status().isOk())
		.andExpect(view().name("editoriales/editEditorial"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testProcessCreationFormEditorialWrongEmail() throws Exception {
		mockMvc.perform(post("/editoriales/save")
				.with(csrf())
				.param("nombre", "Norma")
				.param("nif", "A1231123B")
				.param("direccion", "calle Ejemplo, 13, Barcelona")
				.param("telefono", "650666999")
				.param("email", "om")
				.param("web", "www.norma.com"))
		.andExpect(model().attributeHasErrors("editorial"))
		.andExpect(status().isOk())
		.andExpect(view().name("editoriales/editEditorial"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testProcessCreationFormEditorialWrongWeb() throws Exception {
		mockMvc.perform(post("/editoriales/save")
				.with(csrf())
				.param("nombre", "Norma")
				.param("nif", "A1231123B")
				.param("direccion", "calle Ejemplo, 13, Barcelona")
				.param("telefono", "650666999")
				.param("email", "norma@mail.com")
				.param("web", "om"))
		.andExpect(model().attributeHasErrors("editorial"))
		.andExpect(status().isOk())
		.andExpect(view().name("editoriales/editEditorial"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testInitCreationFormEditorial() throws Exception {
		mockMvc.perform(get("/editoriales/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("editoriales/editEditorial"))
		.andExpect(model().attributeExists("editorial"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testVerLibrosEditorial() throws Exception {
		mockMvc.perform(get("/editoriales/{editorialId}",1))
			.andExpect(status().isOk())
			.andExpect(view().name("editoriales/librosEditorial"))
			.andExpect(model().attributeExists("editorial"))
			.andExpect(model().attributeExists("libros"));
		
	}
	
	
	
}
