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
import org.springframework.samples.petclinic.service.MiembroService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=MiembroController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class MiembroControllerTests {

	@Autowired
	MiembroController controller;
	
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


		given(this.miembroService.findById(1)).willReturn(Optional.of(miembro));
		given(this.userService.findUser("alecasgar")).willReturn(Optional.of(usuario));
		
	}
	
	
	@WithMockUser(value = "Us3r")
	@Test
	void testMiembrosList() throws Exception {
		mockMvc.perform(get("/miembros"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("miembros"))
			.andExpect(view().name("miembros/listMiembro"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/miembros/new"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("miembro"))
			.andExpect(view().name("miembros/editMiembro"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/miembros/save").param("nombre", "A").param("apellidos", "A")
				.param("dni", "00000000A").param("telefono", "000000000").param("email", "a@a.a")
				.param("user.username", "usuarioejemplo").param("user.pass","Pass1234")
			.with(csrf()))
			.andExpect(model().attribute("message", "Miembro guardado correctamente"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/miembros/save")
						.with(csrf())
						.param("nombre", "Prueba").param("apellidos", "A")
						.param("dni", "00000000A").param("telefono", "000000000"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("miembro"))
			.andExpect(model().attributeHasFieldErrors("miembro", "email"))
			.andExpect(view().name("miembros/editMiembro"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormHasErrorsInPhoneFormat() throws Exception {
		mockMvc.perform(post("/miembros/save")
						.with(csrf())
						.param("nombre", "Prueba").param("apellidos", "A")
						.param("dni", "00000000A").param("telefono", "00").param("email", "a@a.a").param("user.username", "usuarioejemplo").param("user.pass","Pass1234"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("miembro"))
			.andExpect(model().attributeHasFieldErrors("miembro", "telefono"))
			.andExpect(view().name("miembros/editMiembro"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormHasErrorsInEmailFormat() throws Exception {
		mockMvc.perform(post("/miembros/save")
						.with(csrf())
						.param("nombre", "Prueba").param("apellidos", "A")
						.param("dni", "00000000A").param("telefono", "000000000").param("email", "a").param("user.username", "usuarioejemplo").param("user.pass","Pass1234"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("miembro"))
			.andExpect(model().attributeHasFieldErrors("miembro", "email"))
			.andExpect(view().name("miembros/editMiembro"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testProcessCreationFormHasErrorsInDNIFormat() throws Exception {
		mockMvc.perform(post("/miembros/save")
						.with(csrf())
						.param("nombre", "Prueba").param("apellidos", "A")
						.param("dni", "000A").param("telefono", "000000000").param("email", "a@a.a").param("user.username", "usuarioejemplo").param("user.pass","Pass1234"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("miembro"))
			.andExpect(model().attributeHasFieldErrors("miembro", "dni"))
			.andExpect(view().name("miembros/editMiembro"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testGuardarMiembroExistente() throws Exception {
		mockMvc.perform(post("/miembros/save").param("nombre", "A").param("apellidos", "A")
				.param("dni", "49586958D").param("telefono", "000000000").param("email", "a@a.a")
				.param("user.username", "alecasgar").param("user.pass","Pass1234")
			.with(csrf()))
			.andExpect(model().attribute("message", "Usuario ya existente"));
	}
	
	@WithMockUser(value = "Us3r")
    @Test
    void testDeshabilitarMiembroSuccess() throws Exception {
		mockMvc.perform(get("/miembros/deshabilitar/{miembroId}", 1))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "Miembro deshabilitado correctamente"))
			.andExpect(view().name("miembros/listMiembro"));
	}

	@WithMockUser(value = "Us3r")
    @Test
    void testDeshabilitarMiembroNoExistente() throws Exception {
		mockMvc.perform(get("/miembros/deshabilitar/{miembroId}", 2))
			.andExpect(status().isOk())
			.andExpect(model().attribute("message", "Miembro no encontrado"))
			.andExpect(view().name("miembros/listMiembro"));
	}
}
