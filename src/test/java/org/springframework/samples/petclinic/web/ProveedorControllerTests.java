package org.springframework.samples.petclinic.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=ProveedorController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class ProveedorControllerTests {

	@Autowired
	ProveedorController controller;
	
	@MockBean
	ProveedorService proveedorService;
	
	@MockBean
	UserService userService;

	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(value = "Us3r")
	@Test
	void testProveedoresList() throws Exception {
		mockMvc.perform(get("/proveedores"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("proveedor"))
			.andExpect(view().name("proveedores/listProveedor"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/proveedores/new"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("proveedor"))
			.andExpect(view().name("proveedores/editProveedor"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/proveedores/save").param("nombre", "A").param("nif", "A1234567A")
				.param("direccion", "B").param("telefono", "000000000").param("email", "A@A.A")
			.with(csrf()))
			.andExpect(model().attribute("message", "Proveedor guardado correctamente"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/proveedores/save")
						.with(csrf())
						.param("nombre", "Prueba").param("nif", "A1234567A")
						.param("direccion", "B").param("telefono", "000000000"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("proveedor"))
			.andExpect(model().attributeHasFieldErrors("proveedor", "email"))
			.andExpect(view().name("proveedores/editProveedor"));
	}
	
}
