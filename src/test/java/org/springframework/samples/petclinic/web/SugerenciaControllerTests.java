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
import org.springframework.samples.petclinic.service.MiembroService;
import org.springframework.samples.petclinic.service.SugerenciaService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.exceptions.LibroNoExistenteException;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(controllers=SugerenciaController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class SugerenciaControllerTests {
	
	@Autowired
	SugerenciaController controller;
	
	@MockBean
	SugerenciaService sugerenciaService;
	
	@MockBean
	MiembroService miembroService;
		
	@MockBean
 	UserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		User usuario = new User();
		usuario.setUsername("alecasgar");
		usuario.setPassword("Alex123456");
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
	}
	
	@WithMockUser(value = "alecasgar")
	@Test
	void testSugerenciaList() throws Exception {
		mockMvc.perform(get("/sugerencias"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("sugerencias"))
			.andExpect(view().name("sugerencias/listSugerencia"));
	}
	
	
	@WithMockUser(value = "alecasgar")
    @Test
    void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/sugerencias/new"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("sugerencia"))
			.andExpect(view().name("sugerencias/editSugerencia"));
	}
	
	
	@WithMockUser(value = "alecasgar")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/sugerencias/save")
				.param("tituloLibro", "A")
				.param("nombreAutor", "A")
			.with(csrf()))
			.andExpect(view().name("sugerencias/listSugerencia"))
			.andExpect(model().attribute("message", "Sugerencia guardada correctamente"));
	}
	
	
//	@WithMockUser(value = "Us3r")
//    @Test
//    void testProcessCreationFormHasErrors() throws Exception {
//		mockMvc.perform(post("/sugerencias/save")
//						.param("titulo_libro", "Prueba")
//			.with(csrf()))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("sugerencia"))
//			.andExpect(model().attributeHasFieldErrors("sugerencia","nombre_autor"))
//			.andExpect(view().name("sugerencias/editSugerencia"));
//	}

}
