package org.springframework.samples.petclinic.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Bibliotecario;
import org.springframework.samples.petclinic.model.Novedad;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.BibliotecarioService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers=BibliotecarioController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class BibliotecarioControllerTests {
	
	
	
	
	@Autowired
	BibliotecarioController controller;
	
	@MockBean
	BibliotecarioService bibliotecarioService;
	
	@MockBean
	UserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		
		User usuario = new User();
		usuario.setUsername("alecagar");
		usuario.setPassword("Ale123456");
		usuario.setEnabled(true);
	
		
		Bibliotecario bibliotecario = new Bibliotecario();
		bibliotecario.setId(1);
		bibliotecario.setNombre("Alejandro");
		bibliotecario.setApellidos("Castro Garcia");
		bibliotecario.setDni("49586958D");
		bibliotecario.setTelefono("123456789");
		bibliotecario.setEmail("alecagar@gmail.com");
		bibliotecario.setUser(usuario);
		bibliotecario.setNovedades(new HashSet<Novedad>());
		
		
		given(this.bibliotecarioService.findById(1)).willReturn(Optional.of(bibliotecario));
		
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testBibliotecariosList() throws Exception {
		mockMvc.perform(get("/bibliotecarios"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("bibliotecarios"))
			.andExpect(view().name("bibliotecarios/listBibliotecario"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testProcessCreationFormBibliotecarioSuccess() throws Exception {
		mockMvc.perform(post("/bibliotecarios/save")
				.with(csrf())
				.param("nombre", "Manuel")
				.param("apellidos", "Ruiz Lorca")
				.param("dni", "49387445P")
				.param("telefono", "650666999")
				.param("email", "marulo@gmail.com")
				.param("username", "marulo"))
		.andExpect(status().isOk())
		.andExpect(view().name("bibliotecarios/listBibliotecario"));
				
		
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testProcessCreationFormBibliotecarioHasErrors() throws Exception {
		mockMvc.perform(post("/bibliotecarios/save")
				.with(csrf())
				.param("nombre", "Manuel")
				.param("apellidos", "Ruiz Lorca")
				.param("dni", "49387445P")
				.param("telefono", "650666947847474747474474747474799")
				.param("email", "marulo@gmail.com")
				.param("username", "marulo"))
		.andExpect(model().attributeHasErrors("bibliotecario"))
		.andExpect(status().isOk())
		.andExpect(view().name("bibliotecarios/editBibliotecario"));
				
		
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testInitCreationFormBibliotecario() throws Exception {
		mockMvc.perform(get("/bibliotecarios/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("bibliotecarios/editBibliotecario"))
		.andExpect(model().attributeExists("bibliotecario"));
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testHabiblitarBibliotecarioSuccess() throws Exception {
		mockMvc.perform(get("/bibliotecarios/habilitar/{biblioId}",1))
		.andExpect(status().isOk())
		.andExpect(view().name("bibliotecarios/listBibliotecario"))
		.andExpect(model().attributeExists("bibliotecarios"));
		
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testHabiblitarBibliotecarioHasErrors() throws Exception {
		mockMvc.perform(get("/bibliotecarios/habilitar/{biblioId}",5))
		.andExpect(status().isOk())
		.andExpect(view().name("bibliotecarios/listBibliotecario"))
		.andExpect(model().attributeExists("bibliotecarios"));
		
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testDeshabilitarBibliotecarioSuccess() throws Exception {
		mockMvc.perform(get("/bibliotecarios/deshabilitar/{biblioId}",1))
		.andExpect(status().isOk())
		.andExpect(view().name("bibliotecarios/listBibliotecario"))
		.andExpect(model().attributeExists("bibliotecarios"));
		
	}
	
	@WithMockUser(value = "Us3r")
	@Test
	void testDeshabilitarBibliotecarioHasErrors() throws Exception {
		mockMvc.perform(get("/bibliotecarios/deshabilitar/{biblioId}",5))
		.andExpect(status().isOk())
		.andExpect(view().name("bibliotecarios/listBibliotecario"))
		.andExpect(model().attributeExists("bibliotecarios"));
		
	}
	
	
	
//	@WithMockUser(value = "Us3r")
//	@Test
//	void testInitCreationForm() throws Exception {
//		mockMvc.perform(get("/bibliotecarios/new"))
//		.andExpect(status().isOk())
//		.andExpect(model().attributeExists("bibliotecario"))
//		.andExpect(view().name("bibliotecarios/editBibliotecario"));
//	}
	
	/*@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/biliotecarios/save").param("nombre", "Natalia").param("apellidos", "Cabeza Ramirez")
						.param("dni", "49486598Q").param("telefono", "650606789").param("email", "nacara12@gmail.com")
						.param("username", "nacara").with(csrf()))
			.andExpect(model().attribute("message", "Bibliotecario guardada correctamente."));
	}*/
	
//	@WithMockUser(value = "Us3r")
//    @Test
//    void testProcessCreationFormHasErrors() throws Exception {
//		mockMvc.perform(post("/bibliotecarios/save")
//						.with(csrf())
//						.param("nombre", "P"))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("bibliotecario"))
//			.andExpect(model().attributeHasFieldErrors("bibliotecario", "apellidos"))
//			.andExpect(view().name("bibliotecarios/editBibliotecario"));
//	}

}
